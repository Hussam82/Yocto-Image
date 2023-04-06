# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# Unable to find any files that looked like license statements. Check the accompanying
# documentation and source headers and set LICENSE and LIC_FILES_CHKSUM accordingly.
#
# NOTE: LICENSE is being set to "CLOSED" to allow you to at least start building - if
# this is not accurate with respect to the licensing of the software being built (it
# will not be in most cases) you must specify the correct value before using this
# recipe for anything other than initial testing/development!
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
S="${WORKDIR}" 
SRC_URI = "file://main.cpp \
           file://widget.cpp \
           file://widget.h \
           file://CMakeLists.txt \
	   file://login_system_start.sh \
           file://login_system.service "

#file://widget.h
DEPENDS += "qtbase"
# NOTE: unable to map the following CMake package dependencies: Qt QT
inherit cmake
inherit cmake_qt5


SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE_${PN} = "login_system.service"


