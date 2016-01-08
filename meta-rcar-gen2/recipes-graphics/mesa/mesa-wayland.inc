# FIXME: can not override PACKAGECONFIG.
PACKAGECONFIG[gles] = "--enable-gles1 --disable-gles2"
PACKAGECONFIG[egl] = "--enable-egl --with-egl-platforms=wayland"

python do_srcrev_check() {
    if d.getVar('SRCREV') == 'INVALID':
        d.setVar('SRCREV', '"%s"' % d.getVarFlags('SRC_URI')['md5sum'])
}
do_patch[prefuncs] += "do_srcrev_check"

# mesa v10.1.3 == ba6dbe2b9cab0b4de840c996b9b6a3ad
# mesa v10.6.3 == 553e525d2f20ed48fca8f1ec3176fd83
FILESEXTRAPATHS_append := ":${THISDIR}/mesa_10.1.3/:${THISDIR}/mesa_10.6.3/"
SRC_URI += " \
   file://0001-egl_dri2-fix-wayland_platform-when-drm_platform-is-n.patch;rev="ba6dbe2b9cab0b4de840c996b9b6a3ad" \
   file://0001-dri2-wayland-fixup-gcc5-build.patch;rev="553e525d2f20ed48fca8f1ec3176fd83" \
"

# Disable the gbm modules of mesa
EXTRA_OECONF_rcar-gen2 := "${@'${EXTRA_OECONF}'.replace('--enable-gbm', '--disable-gbm')}"
# Remove the gbm and egl packages. These are provided in other recipes.
PACKAGES_remove_rcar-gen2 = "libgbm-dev libgbm libegl-mesa-dev libegl-mesa libegl-dev libegl libgles2-mesa libgles2-mesa-dev"

# Remove the x11 configure
PACKAGECONFIG_rcar-gen2 := "${@'${PACKAGECONFIG}'.replace('x11', '')}"

do_install_append_rcar-gen2() {
    # Remove libegl-mesa modules and headers
    rm -f ${D}/usr/lib/libEGL.la
    rm -f ${D}/usr/lib/libEGL.so*
    rm -f ${D}/usr/lib/pkgconfig/egl.pc
    rm -rf ${D}/usr/include/KHR
    rm -rf ${D}/usr/include/EGL
}

# FILES_${PN}-dbg += "${libdir}/src/debug/*"
