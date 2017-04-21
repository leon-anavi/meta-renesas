SUMMARY = "libmemcpy porting from eglibc 2.17"
SECTION = "dev"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780"

require include/rcar-gen2-path-common.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI = "file://libmemcpy-1.0.tar.bz2"

inherit autotools

prefix="${RENESAS_DATADIR}/lib"
exec_prefix="${RENESAS_DATADIR}"

FILES_${PN} += " \
    ${RENESAS_DATADIR} \
"
