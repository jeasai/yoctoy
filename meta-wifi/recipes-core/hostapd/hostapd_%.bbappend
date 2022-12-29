FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append = " file://hostapd.conf file://hostapd"

SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install:append() {
    install -d ${D}${sysconfdir}/default

    install -m 0644 ${WORKDIR}/hostapd ${D}${sysconfdir}/default/hostapd
    install -m 0644 ${WORKDIR}/hostapd.conf ${D}${sysconfdir}/hostapd.conf
}
