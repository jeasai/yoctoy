# Auto-Generated by cargo-bitbake 0.3.16
#
inherit cargo

# If this is git based prefer versioned ones if they exist
# DEFAULT_PREFERENCE = "-1"

# how to get rust-hello-world could be as easy as but default to a git checkout:
# SRC_URI += "crate://crates.io/rust-hello-world/0.1.0"
SRC_URI += "git://git@github.com/jeasai/rust-hello-world.git;protocol=ssh;nobranch=1;branch=main"
SRCREV = "302723017341bf8bbdf50ff0f1d28151ef94cbc5"
S = "${WORKDIR}/git"
CARGO_SRC_DIR = ""
PV:append = ".AUTOINC+3027230173"

# please note if you have entries that do not begin with crate://
# you must change them to how that package can be fetched
SRC_URI += " \
"



LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SUMMARY = "rust-hello-world"
HOMEPAGE = "https://github.com/jeasai/rust-hello-world.git"
LICENSE = "MIT"

# includes this file if it exists but does not fail
# this is useful for anything you may want to override from
# what cargo-bitbake generates.
include rust-hello-world-${PV}.inc
include rust-hello-world.inc