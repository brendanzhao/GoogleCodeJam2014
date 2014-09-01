import itertools
from os import sys

FILEPATH = 'A-large-practice'

sys.stdin = open(FILEPATH + '.in', 'r')
sys.stdout = open(FILEPATH + '.out', 'w')

def get_line(): return raw_input()
def get_int(): return int(get_line())
def get_str(): return str(get_line())

# def generate_char_count_table(strings):
#   char_count_table = [[] for string in strings]

#   for i, string in enumerate(strings):
#     prev = string[0]
#     count = 0
#     for j, current in enumerate(string):
#       if current == prev and j < len(string):
#         count += 1
#       else:
#         char_count_table[i].append(count)
#         count = 1
#       prev = current
#     char_count_table[i].append(count)

#   return char_count_table

# def get_char_medians(char_count_table, unique_char_count, string_count):
#   char_medians = [1] * unique_char_count
#   for i in xrange(unique_char_count):
#     single_char_frequency = {}
#     for j in xrange(string_count):
#       if char_count_table[j][i] in single_char_frequency:
#         single_char_frequency[char_count_table[j][i]] += 1
#       else:
#         single_char_frequency[char_count_table[j][i]] = 1
#     if len(set(single_char_frequency.values())) == 1:
#       char_medians[i] = sum(single_char_frequency.keys()) / len(single_char_frequency.keys())
#       continue
#     max = 0
#     for key, value in single_char_frequency.iteritems():
#       if value > max:
#         max = value
#         median = key
#     for key in [key for key, value single_char_frequency.iteritems() where
#     char_medians[i] = median
#   return char_medians

# def the_repeater(strings):
#   base_strings = set([''.join(c for c, _ in groupby(string)) for string in strings])
#   if len(base_strings) != 1:
#     return 'Fegla Won'

#   unique_char_count = len(list(base_strings)[0])
#   char_count_table = generate_char_count_table(strings)
#   char_medians = get_char_medians(char_count_table, unique_char_count, len(strings))
#   ideal_char_count = sum(char_medians)

#   return sum([abs(ideal_char_count - len(string)) for string in strings])

def the_repeater(strings):
  unique_strs = [''.join(char for char, _ in itertools.groupby(str)) for str in strs]
  unique_str = unique_strs[0]
  if not all(s == unique_str for s in unique_strs):
    return 'Fegla Won'

  all_char_frequencies = [[len(list(chars)) for _, chars in itertools.groupby(str)] for str in strs]
  all_char_frequencies = zip(*all_char_frequencies)

  total_moves = 0
  for char_frequencies in all_char_frequencies:
    moves = []
    move = float('inf')
    for possible_frequency in xrange(min(char_frequencies), max(char_frequencies) + 1):
      moves = min(moves, sum([abs(char_frequency - possible_frequency) \
        for char_frequency in char_frequencies]))
    total_moves += moves

  return total_moves



if __name__ == '__main__':
  for case in xrange(1, get_int() + 1):
    str_count = get_int()
    strs = [get_str() for _ in xrange(str_count)]
    print 'Case #%d: %s' % (case, the_repeater(strs))
