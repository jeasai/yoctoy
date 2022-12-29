#!/bin/bash

YOCTO_VERSION=langdale

clone_repos() {
    if [ ! -d poky ]; then
        if ! git clone -b "${YOCTO_VERSION}" git://git.yoctoproject.org/poky; then
            echo "Could not clone git://git.yoctoproject.org/poky";
            exit 1;
        fi;
    fi;

    if [ ! -d meta-raspberrypi ]; then
        if ! git clone -b "${YOCTO_VERSION}" git@github.com:agherzan/meta-raspberrypi.git; then
            echo "Could not clone git@github.com:agherzan/meta-raspberrypi.git";
            exit 1;
        fi;
    fi;

    if [ ! -d meta-openembedded ]; then
        if ! git clone -b "${YOCTO_VERSION}" git@github.com:openembedded/meta-openembedded.git; then
            echo "Could not clone git@github.com:openembedded/meta-openembedded.git";
            exit 1;
        fi;
    fi;

    return 0;
}

source_oe_init() {
    if ! source poky/oe-init-build-env build/; then
        echo "Could not source oe-init-build-env";
        exit 1;
    fi;

    cd ..

    return 0;
}

add_layers() {
    cd build/

    declare -a layers=("meta-raspberrypi"
                       "meta-kernel" 
                        "meta-wifi"
                        "meta-yoctoy-app"
                        "meta-openembedded/meta-oe"
                        "meta-openembedded/meta-networking"
                        "meta-openembedded/meta-webserver")

    for i in "${layers[@]}"
    do
        if ! bitbake-layers add-layer ../"${i}"; then
            echo "Could not add layer ../${i}";
            exit 1;
        fi
    done

    cd ..
}

build_image() {
    bitbake yoctoy
    cp build/tmp/deploy/images/raspberrypi3-64/yoctoy-raspberrypi3-64.wic.bz2 ./
}

main() {
    if [ ! -d "build/" ]; then
        clone_repos
        source_oe_init
        add_layers
    else
        source_oe_init
    fi;

    build_image
}

main
