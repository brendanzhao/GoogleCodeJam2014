import sys

FILEPATH = 'B-large-practice'

sys.stdin = open(FILEPATH + '.in', 'r')
sys.stdout = open(FILEPATH + '.out', 'w')

def get_line(): return raw_input()
def get_int(): return int(get_line())
def get_ints(): return [int(x) for x in get_line().split()]

def max_two_numbers(numbers):
  largest = 0
  second_largest = 0

  for number in numbers:
    if number > largest:
      second_largest = largest
      largest = number
    elif number > second_largest:
      second_largest = number

  return largest + second_largest

def max_subtree(vertex, parent):
  subtree = []
  children = vertex.adjacent[:]
  if parent:
    children.remove(parent)

  if len(children) < 2:
    return 1

  for child in children:
    subtree.append(max_subtree(child, vertex))

  return 1 + max_two_numbers(subtree)

def full_binary_tree(node_count, edges):
  min_deletions = float('inf')
  vertices = [Vertex(i) for i in xrange(1, node_count + 1)]

  for v, w in edges:
    vertices[v - 1].add_adjacent(vertices[w - 1])
    vertices[w - 1].add_adjacent(vertices[v - 1])

  for root in vertices:
    min_deletions = min(min_deletions, node_count - max_subtree(root, None))

  return min_deletions

class Vertex(object):
  def __init__(self, id):
    self.id = id
    self.adjacent = []

  def add_adjacent(self, vertex):
    self.adjacent.append(vertex)

  def remove_adjacent(self, vertex):
    self.adjacent.remove(vertex)

if __name__ == '__main__':
  for case in xrange(1, get_int() + 1):
    node_count = get_int()
    edges = [get_ints() for _ in xrange(node_count - 1)]
    print 'Case #%d: %d' % (case, full_binary_tree(node_count, edges))
