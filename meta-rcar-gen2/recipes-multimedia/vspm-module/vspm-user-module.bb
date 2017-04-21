require ../../include/rcar-gen2-modules-common.inc

LICENSE = "CLOSED"
DEPENDS = "vspm-kernel-module"
PN = "vspm-user-module"
PR = "r0"
SRC_URI = "file://vspm-user.tar.bz2"

S = "${WORKDIR}"

do_compile() {
    # Build shared library
    cd ${S}/vspm/if
    rm -rf ${S}/vspm/if/libvspm.so*
    make all ARCH=arm
    # Copy shared library for reference from other modules
    cp -P ${S}/vspm/if/libvspm.so* ${LIBSHARED}
}

do_install() {
    # Create destination folder
    mkdir -p ${D}${RENESAS_DATADIR}/lib/ ${D}${RENESAS_DATADIR}/include
    # Copy shared library
    cp -P ${S}/vspm/if/libvspm.so* ${D}${RENESAS_DATADIR}/lib/
    # Copy shared header files
    cp -f ${BUILDDIR}/include/vspm_public.h ${D}${RENESAS_DATADIR}/include
    cp -f ${BUILDDIR}/include/vsp_drv.h ${D}${RENESAS_DATADIR}/include
    cp -f ${BUILDDIR}/include/tddmac_drv.h ${D}${RENESAS_DATADIR}/include
}

do_clean_source() {
    rm -f ${LIBSHARED}/libvspm.so*
    rm -f ${BUILDDIR}/include/vspm_public.h
    rm -f ${BUILDDIR}/include/vsp_drv.h
    rm -f ${BUILDDIR}/include/tddmac_drv.h
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
"

FILES_${PN} = " \
    ${RENESAS_DATADIR}/lib/libvspm.so.* \
"

FILES_${PN}-dev = " \
    ${RENESAS_DATADIR}/lib \
    ${RENESAS_DATADIR}/lib/libvspm.so \
    ${RENESAS_DATADIR}/lib/* \
    ${RENESAS_DATADIR}/include \
    ${RENESAS_DATADIR}/include/*.h \
"

RPROVIDES_${PN} += "vspm-user-module"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP_${PN} += "libdir"
INSANE_SKIP_${PN}-dev += "libdir"
