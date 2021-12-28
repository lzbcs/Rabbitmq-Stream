class LaserMessage(object):
    def __init__(self, secs, nsecs, intensities, ranges):
        self.secs = secs
        self.nsecs = nsecs
        self.intensities = intensities
        self.ranges = ranges
