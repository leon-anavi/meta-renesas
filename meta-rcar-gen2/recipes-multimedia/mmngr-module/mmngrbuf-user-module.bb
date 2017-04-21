require ../../include/rcar-gen2-modules-common.inc

LICENSE = "CLOSED"
DEPENDS = "mmngrbuf-kernel-module"
PN = "mmngrbuf-user-module"
PR = "r0"
S = "${WORKDIR}/mmngrbuf"
SRC_URI = "file://mmngrbuf.tar.bz2"


do_compile() {
    # Build shared library
    cd ${S}/if
    rm -rf ${S}/if/libmmngrbuf.so*
    make all ARCH=arm
    # Copy shared library into shared folder
    cp -P ${S}/if/libmmngrbuf.so* ${LIBSHARED}
}

do_install() {
    mkdir -p ${D}${RENESAS_DATADIR}/lib/ ${D}${RENESAS_DATADIR}/include

    # Copy shared library
    cp -P ${S}/if/libmmngrbuf.so* ${D}${RENESAS_DATADIR}/lib/
    cd ${D}${RENESAS_DATADIR}/lib/
    # Copy shared header files
    cp -f ${BUILDDIR}/include/mmngr_buf_user_public.h ${D}${RENESAS_DATADIR}/include
    cp -f ${BUILDDIR}/include/mmngr_buf_user_private.h ${D}${RENESAS_DATADIR}/include
}

# Append function to clean extract source
do_cleansstate_prepend() {
        bb.build.exec_func('do_clean_source', d)
}

do_clean_source() {
    rm -f ${LIBSHARED}/libmmngrbuf.so*
    rm -Rf ${BUILDDIR}/include/mmngr_buf_user_public.h
    rm -Rf ${BUILDDIR}/include/mmngr_buf_user_private.h
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
"

FILES_${PN} = " \
    ${RENESAS_DATADIR}/lib/libmmngrbuf.so.* \
"

FILES_${PN}-dev = " \
    ${RENESAS_DATADIR}/include \
    ${RENESAS_DATADIR}/include/*.h \
    ${RENESAS_DATADIR}/lib \
    ${RENESAS_DATADIR}/lib/libmmngrbuf.so \
"

RPROVIDES_${PN} += "mmngrbuf-user-module"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INSANE_SKIP_${PN} += "libdir"
INSANE_SKIP_${PN}-dev += "libdir"

do_configure[noexec] = "1"

python do_package_ipk_prepend () {
    d.setVar('ALLOW_EMPTY', '1')
}
