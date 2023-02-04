#This image is based on core-image-base
include recipes-core/images/core-image-base.bb


# Only produce the "rpi-sdimg" image format
IMAGE_FSTYPES = "rpi-sdimg"

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
IMAGE_INSTALL += "cmake"
#IMAGE_INSTALL += "openssl"

#Add RPi.GPIO python library
IMAGE_INSTALL += "rpi-gpio"

#Add nano and git
IMAGE_INSTALL += "nano git"

#Add our apps
IMAGE_INSTALL += "hello"

#Add support for  OpenCV and Camera
IMAGE_INSTALL += "objectdetcpp"
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
IMAGE_FEATURES += "x11-base"
MACHINE_FEATURES:append = " vc4graphics xf86-video-fbdev"
DISTRO_FEATURES:append = " opengl x11 fbdev"


#Add support for wifi and bluetooth
IMAGE_INSTALL += "linux-firmware-bcm43430"

#Use network manager
IMAGE_INSTALL += "networkmanager networkmanager-bash-completion networkmanager-nmtui"

#Add kernel modules
IMAGE_INSTALL += "kernel-modules"
IMAGE_INSTALL += "mymod"
KERNEL_MODULE_AUTOLOAD += " test_driver"

# Remove old builds
RM_OLD_IMAGE = "1"

#Remove Splash for RPI
IMAGE_FEATURES:remove = "splash"


