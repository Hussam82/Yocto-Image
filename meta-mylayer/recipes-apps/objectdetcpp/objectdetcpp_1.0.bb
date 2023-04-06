DESCRIPTION   = " this is an application display an image"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://main.cpp \
	   file://CMakeLists.txt \
	   file://object_detection_classes_coco.txt \
	   file://ssd_mobilenet_v2_coco_2018_03_29.pbtxt \
	   file://frozen_inference_graph.pb \
	   file://Street.mp4	"



inherit pkgconfig
DEPENDS = "opencv"
RDEPENDS_${PN} = "libopencv-core libopencv-imgproc libopencv-highgui libopencv-videoio libopencv-imgcodecs"
S = "${WORKDIR}"

inherit cmake



FILES_${PN} = "${bindir}"
