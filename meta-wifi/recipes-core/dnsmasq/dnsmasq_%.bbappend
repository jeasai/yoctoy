FILESEXTRAPATHS:append := ":${THISDIR}/files"

SRC_URI += " \
            file://dnsmasq.conf \
            file://dnsmasq-resolvconf-restart.service \
            file://dnsmasq-noresolvconf-restart.service \
           "

do_install:append() {
    install -d ${D}/etc

    install -m 0644 ${WORKDIR}/dnsmasq.conf ${D}/etc/dnsmasq.conf

    if [ "${@bb.utils.filter('PACKAGECONFIG', 'resolvconf', d)}" ]; then
        install -m 0644 ${WORKDIR}/dnsmasq-resolvconf-restart.service ${D}${systemd_unitdir}/system/dnsmasq.service
    else
        install -m 0644 ${WORKDIR}/dnsmasq-noresolvconf-restart.service ${D}${systemd_unitdir}/system/dnsmasq.service
    fi
}
