FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI_append_armadillo800eva = " file://fbdev-uiomux-register.patch"

require include/rcar-gen2-path-common.inc

EXTRA_OECONF += "\
	--with-inputdrivers=linuxinput \
"

do_configure_prepend_armadillo800eva() {
    sed -i "s,@RENESAS_DATADIR@,${RENESAS_DATADIR},g" \
           ${S}/systems/fbdev/Makefile.am
}
