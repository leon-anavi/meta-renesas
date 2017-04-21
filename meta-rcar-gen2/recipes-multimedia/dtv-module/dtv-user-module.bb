require ../../include/rcar-gen2-modules-common.inc

LICENSE = "CLOSED"
PN = "dtv"
PR = "r0"
SRC_URI = "file://dtv.tar.bz2"
PACKAGE_ARCH = "${MACHINE_ARCH}"

S = "${WORKDIR}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_populate_lic[noexec] = "1"

do_install() {
    # Create share folders
    mkdir -p ${D}${RENESAS_DATADIR}/include/ ${D}${RENESAS_DATADIR}/lib ${D}${RENESAS_DATADIR}/src/dtv/reference

    # Copy share files to destination
    cp -f ${WORKDIR}/dtv/include/*.h ${D}${RENESAS_DATADIR}/include/
    cp -f ${WORKDIR}/dtv/lib/libdtv.a ${D}${RENESAS_DATADIR}/lib
    cp -f ${WORKDIR}/dtv/userfunc/* ${D}${RENESAS_DATADIR}/src/dtv/reference
}

SYSROOT_PREPROCESS_FUNCS += "do_populate_reference_src"

do_populate_reference_src () {
	sysroot_stage_dir ${D}${RENESAS_DATADIR} ${SYSROOT_DESTDIR}${RENESAS_DATADIR}
}

PACKAGES = "\
    ${PN} \
    ${PN}-dev \
    ${PN}-staticdev \
"

FILES_${PN} = ""
ALLOW_EMPTY_${PN} = "1"

FILES_${PN}-dev = " \
    ${RENESAS_DATADIR}/include/*.h \
    ${RENESAS_DATADIR}/src/dtv/reference/*.c \
    ${RENESAS_DATADIR}/src/dtv/reference/*.h \
"

FILES_${PN}-staticdev = " \
    ${RENESAS_DATADIR}/lib/*.a \
"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

do_configure[noexec] = "1"
