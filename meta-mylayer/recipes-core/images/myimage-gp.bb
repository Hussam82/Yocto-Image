#This image is based on core-image-base
include recipes-core/images/core-image-base.bb

# The resulting SDK will include the required development tools for cross-compiling a Qt application
inherit populate_sdk_qt5

# Only produce the "rpi-sdimg" image format
IMAGE_FSTYPES = "wic rpi-sdimg"

#Add extra space in K for user data and extra packages
#IMAGE_ROOTFS_EXTRA_SPACE = "1000000"

#Add support for ssh and tools-sdk you will find the rest in core-image class
IMAGE_FEATURES += "ssh-server-dropbear"
#IMAGE_FEATURES += "tools_sdk"

#Add package management support
IMAGE_FEATURES += "package-management"
IMAGE_INSTALL += "opkg"

#Add python3 and its packages
IMAGE_INSTALL += "python3 python3-pip python3-modules"

#Add cmake
#IMAGE_INSTALL += "cmake"
#IMAGE_INSTALL += "openssl"

#Add RPi.GPIO python library
IMAGE_INSTALL += "rpi-gpio"

#Add nano and git
IMAGE_INSTALL += "nano git"



#Add our apps
IMAGE_INSTALL += "qtapp"
IMAGE_INSTALL += "lanedetection"
IMAGE_INSTALL += "objectdetcpp"
#IMAGE_INSTALL += "cinematicexperience"

#Qt5
IMAGE_INSTALL += "qtbase-tools qtbase qtdeclarative qtimageformats qtmultimedia qtquickcontrols2 qtquickcontrols qtbase-plugins liberation-fonts qtwayland"

# Add support for qt5 keyboard
#IMAGE_INSTALL += "qtvirtualkeyboard"
#IMAGE_INSTALL += "maliit-framework-qt5"


#Add support for  OpenCV and Camera
IMAGE_INSTALL += "opencv"
IMAGE_INSTALL += "python3-opencv"
IMAGE_INSTALL += "libopencv-core libopencv-imgproc libopencv-highgui libopencv-videoio libopencv-imgcodecs"
IMAGE_INSTALL += "ffmpeg"
IMAGE_INSTALL += "gstreamer1.0 gstreamer1.0-libav gstreamer1.0-plugins-base gstreamer1.0-meta-base gstreamer1.0-plugins-bad gstreamer1.0-plugins-ugly gstreamer1.0-plugins-good"
#IMAGE_INSTALL += "omxplayer"
#IMAGE_INSTALL += "vlc"
IMAGE_INSTALL += "fswebcam"
IMAGE_INSTALL += "v4l-utils"
IMAGE_INSTALL += "userland"
IMAGE_INSTALL += "weston weston-init"
#IMAGE_FEATURES += "x11-base"

#Add Tensor Flow Lite
IMAGE_INSTALL += "python3-tensorflow-lite libtensorflow-lite"


MACHINE_FEATURES:append = " xf86-video-fbdev"



#Add support for wifi and bluetooth
IMAGE_INSTALL += "linux-firmware-bcm43430"

#Use network manager
IMAGE_INSTALL += "networkmanager networkmanager-bash-completion networkmanager-nmtui"
#IMAGE_INSTALL += "wpa-supplicant"

#Add kernel modules
IMAGE_INSTALL += "kernel-modules"
IMAGE_INSTALL += "mymod"
IMAGE_INSTALL += "dht11km"

# Remove old builds
RM_OLD_IMAGE = "1"

# Customize the splash screen or disable
#SPLASH = "psplash-raspberrypi"
#IMAGE_FEATURES += "splash"
IMAGE_FEATURES:remove = "splash"


