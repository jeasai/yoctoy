SUMMARY = "Systemd startup script for our hello device driver"
DESCRIPTION = "Adds a systemd script to create the hello node"
AUTHOR = "Jean Sainctavit"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

SRC_URI += " file://mknod-hello.service"
SRC_URI += " file://mknod-hello.sh"

FILES:${PN} += "${systemd_unitdir}/system/mknod-hello.service ${bindir}/mknod-hello.sh ${systemd_unitdir}/system"

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN} = "mknod-hello.service"

S="${WORKDIR}"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 mknod-hello.sh ${D}${bindir}/

    install -d ${D}${systemd_unitdir}/system
    install -m 0644 mknod-hello.service ${D}${systemd_unitdir}/system/
}

REQUIRED_DISTRO_FEATURES= "systemd"
