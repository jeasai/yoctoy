FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append = " file://index.html file://main.js file://styles.css file://default_server_yoctoy"

do_install:append() {
    install -m 0644 ${WORKDIR}/default_server_yoctoy ${D}${sysconfdir}/nginx/sites-enabled/default_server
    install -m 0644 ${WORKDIR}/index.html ${D}${NGINX_WWWDIR}/html/
    install -m 0644 ${WORKDIR}/main.js ${D}${NGINX_WWWDIR}/html/
    install -m 0644 ${WORKDIR}/styles.css ${D}${NGINX_WWWDIR}/html/
}
