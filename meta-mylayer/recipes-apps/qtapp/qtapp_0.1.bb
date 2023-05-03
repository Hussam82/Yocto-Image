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
SRCREV = "b8c651277d77c1ce4490b6c83f94f83757d12e42"
SRC_URI = "git://github.com/Hussam82/QtGui;protocol=https;branch=main"
SRC_URI[sha256sum] = "2f7e5cbcc90c5626c2bc82a203232a81ec438d2492e9f0a8d17f0297f2b17109"

S="${WORKDIR}/git" 

DEPENDS += "qtbase weston"
# NOTE: unable to map the following CMake package dependencies: Qt QT
inherit cmake
inherit cmake_qt5
inherit systemd

SYSTEMD_SERVICE:${PN} = "qtapp.service"
SYSTEMD_AUTO_ENABLE = "enable"


