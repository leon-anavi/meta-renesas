require ../../include/rcar-gen2-modules-common.inc

LICENSE = "CLOSED"
DEPENDS = "mmngr-kernel-module mmngr-user-module"
SRC_URI = "file://mmngr-tp-user.tar.bz2"

S = "${WORKDIR}/mmngr"

do_compile() {
    # Build test kernel module
    cd ${S}
    make all ARCH=arm
}

do_install() {
    # Copy kernel test program
    mkdir -p ${D}${RENESAS_DATADIR}/bin/
    cp ${S}/mmtp ${D}${RENESAS_DATADIR}/bin/
}

PACKAGES = "\
    ${PN} \
"
FILES_${PN} = " \
    ${RENESAS_DATADIR}/bin/mmtp \
"

RPROVIDES_${PN} += "mmngr-tp-user-module"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
