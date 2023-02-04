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


do_install () {
    install -d 0755 "${D}${bindir}"
    install -m 0755 objectdetcpp "${D}${bindir}"
    install -d 0755 "${D}${sysconfdir}"
    
    install -m 0755 ../object_detection_classes_coco.txt ${D}${sysconfdir}/object_detection_classes_coco.txt
    install -m 0755 ../ssd_mobilenet_v2_coco_2018_03_29.pbtxt ${D}${sysconfdir}/ssd_mobilenet_v2_coco_2018_03_29.pbtxt
    install -m 0755 ../frozen_inference_graph.pb ${D}${sysconfdir}/frozen_inference_graph.pb
    install -m 0755 ../Street.mp4 ${D}${sysconfdir}/Street.mp4
}

FILES_${PN} = "${bindir}"
