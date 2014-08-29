import os.path
import sys

mine = '*'
non_mine = '.'
clicked = 'c'

def fill(grid, r, c, m):
  if r > 2 and c <= r and m >= c:
    for i in xrange(c):
      grid[r - 1][i] = mine
    return fill(grid, r - 1, c, m - c)
  if c > 2 and r <= c and m >= r:
    for i in xrange(r):
      grid[i][c - 1] = mine
    return fill(grid, r, c - 1, m - r)
  for i in xrange(r - 1, 1, -1):
    for j in xrange(c - 1, 1, -1):
      if not m:
         return m
      grid[i][j] = mine
      m -= 1
  return m

def mine_sweeper_master():
  basepath = os.path.dirname(__file__)
  filepath = os.path.abspath(os.path.join(basepath, "..", "C-large-practice.in"))
  for i, line in enumerate(open(filepath, 'r'), start=0):
    if i == 0:
      continue

    print 'Case #%d:' % i

    split_line = line.split()

    r, c, m = map(int, split_line)
    grid = [c * [non_mine] for _ in xrange(r)]

    if m == r * c - 1:
      grid = [c * [mine] for _ in xrange(r)]
    elif fill(grid, r, c, m):
      print 'Impossible'
      continue

    grid[0][0] = clicked

    for i in xrange(r):
      print ''.join(grid[i])

mine_sweeper_master()
