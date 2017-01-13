PACKAGECONFIG ??= " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'pulseaudio', 'pulseaudio', '', d)} \
    cairo flac gdk-pixbuf jpeg libpng soup speex taglib \
    "
