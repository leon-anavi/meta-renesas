DEPENDS += "faad2 libxml2 libuiomux libshvio"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

TARGET_CFLAGS += "-D_GNU_SOURCE"

PACKAGECONFIG := "${@'${PACKAGECONFIG}'.replace('curl', '')}"
PACKAGECONFIG := "${@'${PACKAGECONFIG}'.replace('eglgles', '')}"
PACKAGECONFIG += "faad directfb"

EXTRA_OECONF += "--enable-experimental --disable-nls"
