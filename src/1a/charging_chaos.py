import sys

FILEPATH = 'A-large-practice'
FLIP = '1'
NO_FLIP = '0'

sys.stdin = open(FILEPATH + '.in', 'r')
sys.stdout = open(FILEPATH + '.out', 'w')

def get_line(): return raw_input()
def get_int(): return int(get_line())
def get_ints(): return [int(x) for x in get_line().split()]
def get_binary_as_int(): return [int(x, 2) for x in get_line().split()]

def charging_chaos(n, l, outlets, devices):
  xor_map = [set([outlet ^ device for device in devices]) for outlet in outlets]

  possible_flips = xor_map[0]
  for outlet in xor_map:
    possible_flips = possible_flips.intersection(outlet)

  if len(possible_flips):
    flips = min(possible_flips)
    return bin(flips).count('1')
  else:
    return 'NOT POSSIBLE'

if __name__ == '__main__':
  for case in xrange(1, get_int() + 1):
    n, l = get_ints()
    outlets = get_binary_as_int()
    devices = get_binary_as_int()
    print 'Case #%d: %s' % (case, charging_chaos(n, l, outlets, devices))
