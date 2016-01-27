FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

# EGL driver does not support the EGL_NO_DISPLAY query parameter
SRC_URI += " \
             file://weston-workaround-for-egl-no-display.patch \
           "

CPPFLAGS_append = " -DEGL_DRIVER_NO_EGL_NO_DISPLAY_SUPPORT=1"
