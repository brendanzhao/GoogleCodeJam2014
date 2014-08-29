import sys

FILEPATH = 'C-large-practice'
MINE = '*'
NON_MINE = '.'
CLICKED = 'c'

sys.stdin = open(FILEPATH + ".in", 'r')
sys.stdout = open(FILEPATH + ".out", 'w')

def get_line(): return raw_input()
def get_int(): return int(get_line())
def get_ints(): return [int(x) for x in get_line().split()]

def fill(grid, r, c, m):
  if r > 2 and c <= r and m >= c:
    for i in xrange(c):
      grid[r - 1][i] = MINE
    return fill(grid, r - 1, c, m - c)
  if c > 2 and r <= c and m >= r:
    for i in xrange(r):
      grid[i][c - 1] = MINE
    return fill(grid, r, c - 1, m - r)
  for i in xrange(r - 1, 1, -1):
    for j in xrange(c - 1, 1, -1):
      if not m:
         return m
      grid[i][j] = MINE
      m -= 1
  return m

def mine_sweeper_master(r, c, m):
    grid = [c * [NON_MINE] for _ in xrange(r)]

    if m == r * c - 1:
      grid = [c * [MINE] for _ in xrange(r)]
    elif fill(grid, r, c, m):
      return 'Impossible'

    grid[0][0] = CLICKED

    grid_display = [''.join(element) for element in grid]
    grid_display = '\n'.join(grid_display)
    return grid_display

if __name__ == '__main__':
  for case in xrange(1, get_int() + 1):
    r, c, m = get_ints()
    print 'Case %d:' % case
    print mine_sweeper_master(r, c, m)
