# we use JavaCC to create the file parser.
# just run "javacc Analyzer.jj" to create the java classes of the parser

# TODO: better comments (only one "#" at the beginning of the line)

# following the list of things used in the parser

# symbols:
SYMBOLS

# axiom:
AXIOM

# long axiom:
PHRASE

# rules:
RULES

# angle:
ANGLE

# turtle interpretation:
FORWARD
TURNLEFT
TURNRIGHT
TURNUP
TURNDOWN
ROLLLEFT
ROLLRIGHT
ABOUTTURN
SAVEPOSITION 
RESTOREPOSITION

# types of grammar:
DOL
SOL
DXL
SXL

# comment symbol:
#

# separator for options:
:

# character for transition rule
->

# blocks:
{
}

# previous in rule:
<

# follow in rule:
>

# null character (deletion):
ε


