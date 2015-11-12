SUMMARY = "ALSA sound configuration"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=3d5968ae2c5badd70c24075cfe58cc1e"

RDEPENDS_${PN} = "alsa-utils"

COMPATIBLE_MACHINE = "(porter|koelsch)"

FILESEXTRAPATHS_prepend := ":${THISDIR}/alsa-utils-config:"

SRC_URI = "file://COPYING \
          "
SRC_URI_append_porter  = "file://asound.state-porter"
SRC_URI_append_koelsch = "file://asound.state-porter"
# SRC_URI_append_silk  = "file://asound.state-silk"

do_configure() {
       cp ${WORKDIR}/COPYING ${S}
       cp ${WORKDIR}/asound.state* ${S}/asound.state
}

do_install() {
       install -d ${D}/${localstatedir}/lib/alsa
       install -m 0644 asound.state ${D}/${localstatedir}/lib/alsa
}

FILES_${PN} += "${localstatedir}"
