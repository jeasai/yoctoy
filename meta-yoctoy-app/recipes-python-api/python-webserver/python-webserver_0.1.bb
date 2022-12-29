SUMMARY = "Simple webserver in python"
DESCRIPTION = "Adds a python3 simple webserver"
AUTHOR = "Jean Sainctavit"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

SRC_URI = "file://webserver.py"
SRC_URI:append = " file://python-webserver.service"

FILES:${PN} += "${systemd_unitdir}/system/python-webserver.service"

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN} = "python-webserver.service"

RDEPENDS:${PN}:append = " python3 python3-core python3-flask"

S="${WORKDIR}"

do_install() {
	install -d ${D}${bindir}
    install -d ${D}/${systemd_unitdir}/system

	install -m 0755 webserver.py ${D}${bindir}
    install -m 0644 python-webserver.service ${D}/${systemd_unitdir}/system/
}
