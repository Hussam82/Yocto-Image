/// Use this file with videos ///

#include "lane-detection.h"
using namespace std;


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
        cout << "Correct Usage: \n./lanedetection -c : for camera \n./lanedetection -v [videoFileName.avi] : for video ";
        
    }
    
}

int main(int argc, char* argv[])
{  

    if (argc < 2 || argc > 3)
    {
        cout << "Wrong Usage \n";
        cout << "Correct Usage: \n./lanedetection -c : for camera \n./lanedetection -v [videoFileName.avi] : for video ";
        return 1;
    }
/* 
    // Load source video
    if (argc != 2) {
        std::cout << "Usage: ./exe path-to-video" << std::endl;
        return -1;
    }
   */

    // Initialize video capture for reading a videofile
   // VideoCapture cap(argv[1]);
    //VideoCapture cap("/home/hussam/Desktop/lane-detection-cpp/videos/dashcam4.mp4");
    VideoCapture cap = returnCap(argc,  argv);
    //VideoCapture cap(0);

    // Check if video can be opened
    if (!cap.isOpened())
    {
        std::cout << "Failed to open videofile!" << std::endl;
        return -1;
    }
    
    // Read and analyze video
    while (true)
    {
        Mat frame;
        cap >> frame;

        // Stop if frame is empty (end of video)
        if (frame.empty())
        {
            break;
        }

        // Determine if video is taken during daytime or not
        bool isDay = isDayTime(frame);

        // Filter image 
        Mat filteredIMG = filterColors(frame, isDay);

        // Apply grayscale
        Mat gray = applyGrayscale(filteredIMG);

        // Apply Gaussian blur
        Mat gBlur = applyGaussianBlur(gray);

        // Find edges
        Mat edges = applyCanny(gBlur);

        // Create mask (Region of Interest)
        Mat maskedIMG = RegionOfInterest(edges);

        // Detect straight lines and draw the lanes if possible
        std::vector<Vec4i> linesP = houghLines(maskedIMG, frame.clone(), false);
        Mat lanes = drawLanes(frame, linesP);
        imshow("Lanes", lanes);

        // Press  ESC on keyboard to exit
        if (waitKey(5) == 27) break;
    }

    cap.release();
   
    return 0;
}
