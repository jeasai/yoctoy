SUMMARY = "Yoctoy image"
LICENSE = "MIT"

inherit core-image extrausers

IMAGE_INSTALL += " \
    makefile-c-github \
    rust-hello-world \
    mknod-hello \
    python-webserver \
    nginx \
    dnsmasq \
    hostapd \
    "
IMAGE_FEATURES += "read-only-rootfs"

IMAGE_FSTYPE += "squashfs"

DISTRO_FEATURES:remove = "bluetooth"
DISTRO_FEATURES:remove = "3g"
DISTRO_FEATURES:remove = "opengl"
DISTRO_FEATURES:remove = "nfc"
DISTRO_FEATURES:remove = "nfs"
DISTRO_FEATURES:remove = "ext2"

EXTRA_USERS_PARAMS = " \
    usermod -p '$(openssl rand -base64 32)' root; \
"
