SUMMARY = "Hello, world make github c"
DESCRIPTION = "Adds a hello world binary from github in /usr/bin/, and patches it"
AUTHOR = "Jean Sainctavit"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/jeasai/c-hello-world.git;branch=main;protocol=https;name=sources"
SRC_URI += "file://hello_patched_world.patch"
SRCREV_sources = "608f14296b3d8ec06ddae2c942c6b042a2ac93d4"

S = "${WORKDIR}/git"

do_compile() {
	oe_runmake c-hello-world
}

do_install() {
	install -d ${D}${bindir}
	install -m 0755 c-hello-world ${D}${bindir}
}
