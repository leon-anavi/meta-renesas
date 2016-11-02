EXTRA_OECONF += "--enable-gst-recorder "
DEPENDS += "media-ctl"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
 	       file://0001-Add-virtual-output-support.patch \
    	       file://0002-Get-DMA-fd-on-bo.patch \
	       file://0003-Add-gst-recorder-for-h264-output-streaming.patch \
               file://weston.service \
"