require ../../include/rcar-gen2-modules-common.inc

LICENSE = "CLOSED"
DEPENDS = "fdpm-kernel-module mmngr-user-module"
PN = "fdpm-user-module"
SRC_URI = "file://fdpm.tar.bz2"

S = "${WORKDIR}/fdpm"

do_compile() {
    # Build shared library
    cd ${S}/if
    rm -rf libfdpm.so*
    make all ARCH=arm
    # Copy shared library for reference from other modules
    cp -P ${S}/if/libfdpm.so* ${LIBSHARED}
    cp -rf ${S}/include/*h ${STAGING_INCDIR}
}

do_install() {
    # Create destination folder
    mkdir -p ${D}${RENESAS_DATADIR}/lib/ ${D}${RENESAS_DATADIR}/include/

    # Copy shared library
    cp -P ${S}/if/libfdpm.so* ${D}${RENESAS_DATADIR}/lib
    cp -rf ${S}/include/*h ${D}${RENESAS_DATADIR}/include/
}

# Append function to clean extract source
do_cleansstate_prepend() {
        bb.build.exec_func('do_clean_source', d)
}

do_clean_source() {
    rm -f ${LIBSHARED}/libfdpm.so*
    rm -f ${STAGING_INCDIR}/fdpm_api.h
    rm -f ${STAGING_INCDIR}/fdpm_drv.h
    rm -f ${STAGING_INCDIR}/fdpm_if_fd.h
    rm -f ${STAGING_INCDIR}/fdpm_if.h
    rm -f ${STAGING_INCDIR}/fdpm_if_par.h
    rm -f ${STAGING_INCDIR}/fdpm_if_priv.h
    rm -f ${STAGING_INCDIR}/fdpm_public.h
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
"

FILES_${PN} = " \
    ${RENESAS_DATADIR}/lib/libfdpm.so.* \
"

FILES_${PN}-dev = " \
    ${RENESAS_DATADIR}/lib/libfdpm.so \
    ${RENESAS_DATADIR}/include/*.h \
"

RPROVIDES_${PN} += "fdpm-user-module"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP_${PN} += "libdir"
INSANE_SKIP_${PN}-dev += "libdir"

do_configure[noexec] = "1"

python do_package_ipk_prepend () {
    d.setVar('ALLOW_EMPTY', '1')
}
