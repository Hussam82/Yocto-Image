#include <opencv2/imgcodecs.hpp>
#include <opencv2/highgui.hpp>
#include <opencv2/imgproc.hpp>
#include <opencv2/objdetect.hpp>
#include <opencv2/dnn.hpp>
#include <opencv2/dnn/all_layers.hpp>
#include <iostream>
#include <fstream>
#include <string.h>

using namespace cv;
using namespace std;
using namespace dnn;

VideoCapture returnCap(int argc, char** argv)
{
    cout<<argc<<endl;
    if (argc == 2)
    {
        if (!strcmp(argv[1],"-c") || !strcmp(argv[1],"-camera"))
        {
        	cout<<"Camera ON\n";
            return VideoCapture(0);

        }

    }
    else if (argc == 3)
    {
        if (!strcmp(argv[1],"-v") || !strcmp(argv[1],"-video"))
        {
            return VideoCapture(argv[2]);
        }

    }
    else
    {
    	cout << "Wrong Usage \n";
        cout << "Correct Usage: \n./ObjectDetectionModule -c : for camera \n./ObjectDetectionModule -v [videoFileName.avi] : for video ";
        
    }
    
}


int main(int argc, char** argv)
{
    
    if (argc < 2 || argc > 3)
    {
        cout << "Wrong Usage \n";
        cout << "Correct Usage: \n./ObjectDetectionModule -c : for camera \n./ObjectDetectionModule -v [videoFileName.avi] : for video ";
        return 1;
    }

    string path = "";

    vector<string> class_names;
    ifstream ifs (string(path + "object_detection_classes_coco.txt").c_str());
    string line;

    while (getline(ifs, line))
    {
        cout << line << endl;
        class_names.push_back(line);
    }
    
    auto net = readNet(path + "frozen_inference_graph.pb",
        path + "ssd_mobilenet_v2_coco_2018_03_29.pbtxt", "TensorFlow");


    
    VideoCapture cap = returnCap( argc,  argv);
    //newline
    int frame_width = cap.get(CAP_PROP_FRAME_WIDTH);
    int frame_height = cap.get(CAP_PROP_FRAME_HEIGHT);

    //newline
    VideoWriter video("outcpp_2.avi", VideoWriter::fourcc('X', 'V', 'I', 'D'), 10, Size(frame_width, frame_height) );
    
    net.setPreferableBackend(DNN_BACKEND_CUDA);
    net.setPreferableTarget(DNN_TARGET_CUDA);

    float min_confidence = 0.5;


    while (cap.isOpened())
    {
        Mat image;
        bool isSuccess = cap.read(image);

        if (!isSuccess)
        {
            cout << "Could not Load the image" << endl;
            break;
        }

        int image_height = image.cols;
        int image_width = image.rows;

        auto start = getTickCount();

        Mat blob = blobFromImage(image, 1.0, Size(300, 300), Scalar(127.5, 127.5, 127.5), true, false );

        net.setInput(blob);

        Mat output = net.forward();

        auto end = getTickCount();

        Mat results(output.size[2], output.size[3], CV_32F, output.ptr<float>());

        for (int i = 0; i < results.rows; i++)
        {
            int class_id = int(results.at<float>(i, 1));
            float confidence = results.at<float>(i, 2);

            if (confidence > min_confidence)
            {
                int bboxX = int(results.at<float>(i, 3) * image.cols);
                int bboxY = int(results.at<float>(i, 4) * image.rows);
                int bboxWidth = int(results.at<float>(i, 5) * image.cols - bboxX);
                int bboxHeight = int(results.at<float>(i, 6) * image.rows - bboxY);
                rectangle(image, Point(bboxX, bboxY), Point(bboxX + bboxWidth, bboxY + bboxHeight), Scalar(0, 0, 255), 2);
                string class_name = class_names[class_id - 1];
                putText(image, class_name + " " + to_string(int(confidence * 100)) + "%", Point(bboxX, bboxY - 10), FONT_HERSHEY_COMPLEX, 1.5, Scalar(0, 255, 0), 2);

            }
        }

        auto totalTime = (end - start) / getTickFrequency();
        cout << "Time: " << totalTime << endl;
        putText(image, "FPS: " + to_string(int(1 / totalTime)), Point(50, 50), FONT_HERSHEY_DUPLEX, 1, Scalar(0, 255, 0), 2, false);
        //newline
        video.write(image);

        imshow("image", image);

        int k = waitKey(1);
        if (k == 113)
        {
            break;
        }

    }

}

