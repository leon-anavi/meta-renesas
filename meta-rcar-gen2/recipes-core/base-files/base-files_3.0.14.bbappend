require meta-rcar-gen2/include/multimedia-control.inc
require include/rcar-gen2-path-common.inc

do_install_append () {
	if [ "X${USE_MULTIMEDIA}" = "X1" ] ; then
		echo "export LD_LIBRARY_PATH=\"${RENESAS_DATADIR}/lib\"" >> ${D}${sysconfdir}/profile
	fi
}
