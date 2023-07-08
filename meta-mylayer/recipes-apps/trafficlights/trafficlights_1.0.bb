SUMMARY = "A recipe to build and install traffic lights detection application"
LICENSE = "CLOSED"
RDEPENDS:${PN}:append = " python3-opencv pip-yolov5"
DEPENDS:append = " opencv"
SRC_URI:append = " file://trafficlights.py \
                   file://best_tl2.pt \
                   file://videoplayback_6.mp4"

S = "${WORKDIR}"


do_install:append(){
install -d ${D}${bindir}
cp ${S}/trafficlights.py ${D}${bindir}

install -d ${D}/etc/TrafficLightsDetection
cp -r ${S}/best_tl2.pt ${D}/etc/TrafficLightsDetection
cp ${S}/videoplayback_6.mp4 ${D}/etc/TrafficLightsDetection

}
FILES:${PN}:append = " ${bindir}/*"
FILES:${PN}:append = " /etc/TrafficLightsDetection/*"
