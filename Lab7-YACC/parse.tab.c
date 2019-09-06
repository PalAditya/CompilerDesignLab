/* A Bison parser, made by GNU Bison 2.7.  */

/* Bison implementation for Yacc-like parsers in C
   
      Copyright (C) 1984, 1989-1990, 2000-2012 Free Software Foundation, Inc.
   
   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.
   
   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.
   
   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.
   
   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

/* C LALR(1) parser skeleton written by Richard Stallman, by
   simplifying the original so-called "semantic" parser.  */

/* All symbols defined below should begin with yy or YY, to avoid
   infringing on user name space.  This should be done even for local
   variables, as they might otherwise be expanded by user macros.
   There are some unavoidable exceptions within include files to
   define necessary library symbols; they are noted "INFRINGES ON
   USER NAME SPACE" below.  */

/* Identify Bison output.  */
#define YYBISON 1

/* Bison version.  */
#define YYBISON_VERSION "2.7"

/* Skeleton name.  */
#define YYSKELETON_NAME "yacc.c"

/* Pure parsers.  */
#define YYPURE 0

/* Push parsers.  */
#define YYPUSH 0

/* Pull parsers.  */
#define YYPULL 1




/* Copy the first part of user declarations.  */
/* Line 371 of yacc.c  */
#line 1 "parse.y"
 
#include<stdio.h> 
#include "lex.yy.c"
int flag=0; 
void yyerror(const char *str);

/* Line 371 of yacc.c  */
#line 75 "parse.tab.c"

# ifndef YY_NULL
#  if defined __cplusplus && 201103L <= __cplusplus
#   define YY_NULL nullptr
#  else
#   define YY_NULL 0
#  endif
# endif

/* Enabling verbose error messages.  */
#ifdef YYERROR_VERBOSE
# undef YYERROR_VERBOSE
# define YYERROR_VERBOSE 1
#else
# define YYERROR_VERBOSE 0
#endif

/* In a future release of Bison, this section will be replaced
   by #include "parse.tab.h".  */
#ifndef YY_YY_PARSE_TAB_H_INCLUDED
# define YY_YY_PARSE_TAB_H_INCLUDED
/* Enabling traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif
#if YYDEBUG
extern int yydebug;
#endif

/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     AND = 258,
     ASSIGN = 259,
     COLON = 260,
     COMMA = 261,
     DEF = 262,
     DIV = 263,
     DOT = 264,
     ELSE = 265,
     END = 266,
     EQ = 267,
     EXITLOOP = 268,
     FLOAT = 269,
     FLOAT_CONST = 270,
     FORMAT = 271,
     FROM = 272,
     FUN = 273,
     GE = 274,
     GLOBAL = 275,
     GT = 276,
     ID = 277,
     IF = 278,
     INT = 279,
     INT_CONST = 280,
     LEFT_PAREN = 281,
     LEFT_SQ_BKT = 282,
     LE = 283,
     LT = 284,
     MINUS = 285,
     MOD = 286,
     MULT = 287,
     NE = 288,
     NOT = 289,
     NUL = 290,
     OR = 291,
     PLUS = 292,
     PRINT = 293,
     PRODUCT = 294,
     READ = 295,
     RETURN = 296,
     RETURNS = 297,
     RIGHT_PAREN = 298,
     RIGHT_SQ_BKT = 299,
     SEMICOLON = 300,
     SKIP = 301,
     STEP = 302,
     STRING = 303,
     TO = 304,
     WHILE = 305,
     UPLUS = 306,
     UMINUS = 307,
     IFX = 308
   };
#endif


#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef int YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
#endif

#if ! defined YYLTYPE && ! defined YYLTYPE_IS_DECLARED
typedef struct YYLTYPE
{
  int first_line;
  int first_column;
  int last_line;
  int last_column;
} YYLTYPE;
# define yyltype YYLTYPE /* obsolescent; will be withdrawn */
# define YYLTYPE_IS_DECLARED 1
# define YYLTYPE_IS_TRIVIAL 1
#endif

extern YYSTYPE yylval;
extern YYLTYPE yylloc;
#ifdef YYPARSE_PARAM
#if defined __STDC__ || defined __cplusplus
int yyparse (void *YYPARSE_PARAM);
#else
int yyparse ();
#endif
#else /* ! YYPARSE_PARAM */
#if defined __STDC__ || defined __cplusplus
int yyparse (void);
#else
int yyparse ();
#endif
#endif /* ! YYPARSE_PARAM */

#endif /* !YY_YY_PARSE_TAB_H_INCLUDED  */

/* Copy the second part of user declarations.  */

/* Line 390 of yacc.c  */
#line 207 "parse.tab.c"

#ifdef short
# undef short
#endif

#ifdef YYTYPE_UINT8
typedef YYTYPE_UINT8 yytype_uint8;
#else
typedef unsigned char yytype_uint8;
#endif

#ifdef YYTYPE_INT8
typedef YYTYPE_INT8 yytype_int8;
#elif (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
typedef signed char yytype_int8;
#else
typedef short int yytype_int8;
#endif

#ifdef YYTYPE_UINT16
typedef YYTYPE_UINT16 yytype_uint16;
#else
typedef unsigned short int yytype_uint16;
#endif

#ifdef YYTYPE_INT16
typedef YYTYPE_INT16 yytype_int16;
#else
typedef short int yytype_int16;
#endif

#ifndef YYSIZE_T
# ifdef __SIZE_TYPE__
#  define YYSIZE_T __SIZE_TYPE__
# elif defined size_t
#  define YYSIZE_T size_t
# elif ! defined YYSIZE_T && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
#  include <stddef.h> /* INFRINGES ON USER NAME SPACE */
#  define YYSIZE_T size_t
# else
#  define YYSIZE_T unsigned int
# endif
#endif

#define YYSIZE_MAXIMUM ((YYSIZE_T) -1)

#ifndef YY_
# if defined YYENABLE_NLS && YYENABLE_NLS
#  if ENABLE_NLS
#   include <libintl.h> /* INFRINGES ON USER NAME SPACE */
#   define YY_(Msgid) dgettext ("bison-runtime", Msgid)
#  endif
# endif
# ifndef YY_
#  define YY_(Msgid) Msgid
# endif
#endif

/* Suppress unused-variable warnings by "using" E.  */
#if ! defined lint || defined __GNUC__
# define YYUSE(E) ((void) (E))
#else
# define YYUSE(E) /* empty */
#endif

/* Identity function, used to suppress warnings about constant conditions.  */
#ifndef lint
# define YYID(N) (N)
#else
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static int
YYID (int yyi)
#else
static int
YYID (yyi)
    int yyi;
#endif
{
  return yyi;
}
#endif

#if ! defined yyoverflow || YYERROR_VERBOSE

/* The parser invokes alloca or malloc; define the necessary symbols.  */

# ifdef YYSTACK_USE_ALLOCA
#  if YYSTACK_USE_ALLOCA
#   ifdef __GNUC__
#    define YYSTACK_ALLOC __builtin_alloca
#   elif defined __BUILTIN_VA_ARG_INCR
#    include <alloca.h> /* INFRINGES ON USER NAME SPACE */
#   elif defined _AIX
#    define YYSTACK_ALLOC __alloca
#   elif defined _MSC_VER
#    include <malloc.h> /* INFRINGES ON USER NAME SPACE */
#    define alloca _alloca
#   else
#    define YYSTACK_ALLOC alloca
#    if ! defined _ALLOCA_H && ! defined EXIT_SUCCESS && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
#     include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
      /* Use EXIT_SUCCESS as a witness for stdlib.h.  */
#     ifndef EXIT_SUCCESS
#      define EXIT_SUCCESS 0
#     endif
#    endif
#   endif
#  endif
# endif

# ifdef YYSTACK_ALLOC
   /* Pacify GCC's `empty if-body' warning.  */
#  define YYSTACK_FREE(Ptr) do { /* empty */; } while (YYID (0))
#  ifndef YYSTACK_ALLOC_MAXIMUM
    /* The OS might guarantee only one guard page at the bottom of the stack,
       and a page size can be as small as 4096 bytes.  So we cannot safely
       invoke alloca (N) if N exceeds 4096.  Use a slightly smaller number
       to allow for a few compiler-allocated temporary stack slots.  */
#   define YYSTACK_ALLOC_MAXIMUM 4032 /* reasonable circa 2006 */
#  endif
# else
#  define YYSTACK_ALLOC YYMALLOC
#  define YYSTACK_FREE YYFREE
#  ifndef YYSTACK_ALLOC_MAXIMUM
#   define YYSTACK_ALLOC_MAXIMUM YYSIZE_MAXIMUM
#  endif
#  if (defined __cplusplus && ! defined EXIT_SUCCESS \
       && ! ((defined YYMALLOC || defined malloc) \
	     && (defined YYFREE || defined free)))
#   include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#   ifndef EXIT_SUCCESS
#    define EXIT_SUCCESS 0
#   endif
#  endif
#  ifndef YYMALLOC
#   define YYMALLOC malloc
#   if ! defined malloc && ! defined EXIT_SUCCESS && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
void *malloc (YYSIZE_T); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
#  ifndef YYFREE
#   define YYFREE free
#   if ! defined free && ! defined EXIT_SUCCESS && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
void free (void *); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
# endif
#endif /* ! defined yyoverflow || YYERROR_VERBOSE */


#if (! defined yyoverflow \
     && (! defined __cplusplus \
	 || (defined YYLTYPE_IS_TRIVIAL && YYLTYPE_IS_TRIVIAL \
	     && defined YYSTYPE_IS_TRIVIAL && YYSTYPE_IS_TRIVIAL)))

/* A type that is properly aligned for any stack member.  */
union yyalloc
{
  yytype_int16 yyss_alloc;
  YYSTYPE yyvs_alloc;
  YYLTYPE yyls_alloc;
};

/* The size of the maximum gap between one aligned stack and the next.  */
# define YYSTACK_GAP_MAXIMUM (sizeof (union yyalloc) - 1)

/* The size of an array large to enough to hold all stacks, each with
   N elements.  */
# define YYSTACK_BYTES(N) \
     ((N) * (sizeof (yytype_int16) + sizeof (YYSTYPE) + sizeof (YYLTYPE)) \
      + 2 * YYSTACK_GAP_MAXIMUM)

# define YYCOPY_NEEDED 1

/* Relocate STACK from its old location to the new one.  The
   local variables YYSIZE and YYSTACKSIZE give the old and new number of
   elements in the stack, and YYPTR gives the new location of the
   stack.  Advance YYPTR to a properly aligned location for the next
   stack.  */
# define YYSTACK_RELOCATE(Stack_alloc, Stack)				\
    do									\
      {									\
	YYSIZE_T yynewbytes;						\
	YYCOPY (&yyptr->Stack_alloc, Stack, yysize);			\
	Stack = &yyptr->Stack_alloc;					\
	yynewbytes = yystacksize * sizeof (*Stack) + YYSTACK_GAP_MAXIMUM; \
	yyptr += yynewbytes / sizeof (*yyptr);				\
      }									\
    while (YYID (0))

#endif

#if defined YYCOPY_NEEDED && YYCOPY_NEEDED
/* Copy COUNT objects from SRC to DST.  The source and destination do
   not overlap.  */
# ifndef YYCOPY
#  if defined __GNUC__ && 1 < __GNUC__
#   define YYCOPY(Dst, Src, Count) \
      __builtin_memcpy (Dst, Src, (Count) * sizeof (*(Src)))
#  else
#   define YYCOPY(Dst, Src, Count)              \
      do                                        \
        {                                       \
          YYSIZE_T yyi;                         \
          for (yyi = 0; yyi < (Count); yyi++)   \
            (Dst)[yyi] = (Src)[yyi];            \
        }                                       \
      while (YYID (0))
#  endif
# endif
#endif /* !YYCOPY_NEEDED */

/* YYFINAL -- State number of the termination state.  */
#define YYFINAL  8
/* YYLAST -- Last index in YYTABLE.  */
#define YYLAST   265

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  54
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  44
/* YYNRULES -- Number of rules.  */
#define YYNRULES  100
/* YYNRULES -- Number of states.  */
#define YYNSTATES  188

/* YYTRANSLATE(YYLEX) -- Bison symbol number corresponding to YYLEX.  */
#define YYUNDEFTOK  2
#define YYMAXUTOK   308

#define YYTRANSLATE(YYX)						\
  ((unsigned int) (YYX) <= YYMAXUTOK ? yytranslate[YYX] : YYUNDEFTOK)

/* YYTRANSLATE[YYLEX] -- Bison symbol number corresponding to YYLEX.  */
static const yytype_uint8 yytranslate[] =
{
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,    29,    30,    31,    32,    33,    34,
      35,    36,    37,    38,    39,    40,    41,    42,    43,    44,
      45,    46,    47,    48,    49,    50,    51,    52,    53
};

#if YYDEBUG
/* YYPRHS[YYN] -- Index of the first RHS symbol of rule number YYN in
   YYRHS.  */
static const yytype_uint16 yyprhs[] =
{
       0,     0,     3,     5,    10,    13,    14,    18,    22,    28,
      32,    34,    38,    40,    43,    45,    46,    51,    55,    57,
      59,    61,    63,    65,    67,    73,    79,    81,    83,    84,
      90,    94,    98,   100,   103,   106,   108,   109,   113,   115,
     117,   119,   121,   123,   125,   127,   129,   131,   133,   135,
     139,   141,   145,   149,   152,   156,   163,   166,   167,   173,
     184,   187,   188,   194,   197,   199,   200,   202,   204,   207,
     209,   210,   215,   219,   223,   227,   230,   234,   238,   240,
     242,   244,   246,   248,   250,   254,   258,   262,   266,   270,
     273,   276,   280,   284,   286,   292,   294,   296,   298,   299,
     303
};

/* YYRHS -- A `-1'-separated list of the rules' RHS.  */
static const yytype_int8 yyrhs[] =
{
      55,     0,    -1,    56,    -1,    20,    57,    73,    11,    -1,
      58,    57,    -1,    -1,     7,    59,    11,    -1,    18,    66,
      11,    -1,    59,    45,    60,     5,    64,    -1,    60,     5,
      64,    -1,    65,    -1,    61,     6,    60,    -1,    61,    -1,
      22,    62,    -1,    63,    -1,    -1,    63,    27,    25,    44,
      -1,    27,    25,    44,    -1,    24,    -1,    14,    -1,    48,
      -1,    35,    -1,    65,    -1,    22,    -1,    22,     4,    39,
      59,    11,    -1,    67,    68,    42,    64,    72,    -1,    22,
      -1,    69,    -1,    -1,    69,    45,    70,     5,    64,    -1,
      70,     5,    64,    -1,    70,     6,    71,    -1,    71,    -1,
      22,    62,    -1,    57,    73,    -1,    74,    -1,    -1,    74,
      45,    75,    -1,    75,    -1,    76,    -1,    78,    -1,    79,
      -1,    80,    -1,    82,    -1,    83,    -1,    85,    -1,    86,
      -1,    88,    -1,    89,    -1,    77,     4,    95,    -1,    90,
      -1,    90,     9,    77,    -1,    40,    16,    95,    -1,    38,
      48,    -1,    38,    16,    95,    -1,    23,    93,     5,    74,
      81,    11,    -1,    10,    74,    -1,    -1,    50,    93,     5,
      74,    11,    -1,    17,    90,     4,    95,    49,    95,    84,
       5,    73,    11,    -1,    47,    95,    -1,    -1,    26,    22,
       5,    97,    43,    -1,    41,    87,    -1,    95,    -1,    -1,
      13,    -1,    46,    -1,    22,    91,    -1,    92,    -1,    -1,
      92,    27,    95,    44,    -1,    27,    95,    44,    -1,    93,
      36,    93,    -1,    93,     3,    93,    -1,    34,    93,    -1,
      26,    93,    43,    -1,    95,    94,    95,    -1,    12,    -1,
      28,    -1,    29,    -1,    19,    -1,    21,    -1,    33,    -1,
      95,    37,    95,    -1,    95,    30,    95,    -1,    95,    32,
      95,    -1,    95,     8,    95,    -1,    95,    31,    95,    -1,
      30,    95,    -1,    37,    95,    -1,    95,     9,    95,    -1,
      26,    95,    43,    -1,    90,    -1,    26,    22,     5,    96,
      43,    -1,    25,    -1,    15,    -1,    97,    -1,    -1,    97,
       6,    95,    -1,    95,    -1
};

/* YYRLINE[YYN] -- source line where rule number YYN was defined.  */
static const yytype_uint8 yyrline[] =
{
       0,    25,    25,    30,    32,    33,    35,    36,    38,    39,
      40,    42,    43,    45,    47,    48,    50,    51,    53,    54,
      55,    56,    57,    58,    60,    62,    64,    66,    67,    69,
      70,    72,    73,    75,    77,    79,    80,    82,    83,    85,
      86,    87,    88,    89,    90,    91,    92,    93,    94,    96,
      98,    99,   101,   103,   104,   106,   108,   109,   111,   113,
     115,   116,   118,   120,   122,   123,   125,   127,   129,   131,
     132,   134,   135,   137,   138,   139,   140,   141,   143,   144,
     145,   146,   147,   148,   150,   151,   152,   153,   154,   155,
     156,   157,   158,   159,   160,   161,   162,   164,   165,   167,
     168
};
#endif

#if YYDEBUG || YYERROR_VERBOSE || 0
/* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
   First, the terminals, then, starting at YYNTOKENS, nonterminals.  */
static const char *const yytname[] =
{
  "$end", "error", "$undefined", "AND", "ASSIGN", "COLON", "COMMA", "DEF",
  "DIV", "DOT", "ELSE", "END", "EQ", "EXITLOOP", "FLOAT", "FLOAT_CONST",
  "FORMAT", "FROM", "FUN", "GE", "GLOBAL", "GT", "ID", "IF", "INT",
  "INT_CONST", "LEFT_PAREN", "LEFT_SQ_BKT", "LE", "LT", "MINUS", "MOD",
  "MULT", "NE", "NOT", "NUL", "OR", "PLUS", "PRINT", "PRODUCT", "READ",
  "RETURN", "RETURNS", "RIGHT_PAREN", "RIGHT_SQ_BKT", "SEMICOLON", "SKIP",
  "STEP", "STRING", "TO", "WHILE", "UPLUS", "UMINUS", "IFX", "$accept",
  "S", "prog", "declList", "decl", "typeList", "varList", "var",
  "sizeListO", "sizeList", "type", "typeDef", "funDef", "funID",
  "fParamListO", "fParamList", "pList", "idP", "funBody", "stmtListO",
  "stmtList", "stmt", "assignmentStmt", "dotId", "readStmt", "printStmt",
  "ifStmt", "elsePart", "whileStmt", "loopStmt", "stepPart", "callStmt",
  "returnStmt", "expO", "exitLoop", "skip", "id", "indxListO", "indxList",
  "bExp", "relOP", "exp", "actParamListO", "actParamList", YY_NULL
};
#endif

# ifdef YYPRINT
/* YYTOKNUM[YYLEX-NUM] -- Internal token number corresponding to
   token YYLEX-NUM.  */
static const yytype_uint16 yytoknum[] =
{
       0,   256,   257,   258,   259,   260,   261,   262,   263,   264,
     265,   266,   267,   268,   269,   270,   271,   272,   273,   274,
     275,   276,   277,   278,   279,   280,   281,   282,   283,   284,
     285,   286,   287,   288,   289,   290,   291,   292,   293,   294,
     295,   296,   297,   298,   299,   300,   301,   302,   303,   304,
     305,   306,   307,   308
};
# endif

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const yytype_uint8 yyr1[] =
{
       0,    54,    55,    56,    57,    57,    58,    58,    59,    59,
      59,    60,    60,    61,    62,    62,    63,    63,    64,    64,
      64,    64,    64,    64,    65,    66,    67,    68,    68,    69,
      69,    70,    70,    71,    72,    73,    73,    74,    74,    75,
      75,    75,    75,    75,    75,    75,    75,    75,    75,    76,
      77,    77,    78,    79,    79,    80,    81,    81,    82,    83,
      84,    84,    85,    86,    87,    87,    88,    89,    90,    91,
      91,    92,    92,    93,    93,    93,    93,    93,    94,    94,
      94,    94,    94,    94,    95,    95,    95,    95,    95,    95,
      95,    95,    95,    95,    95,    95,    95,    96,    96,    97,
      97
};

/* YYR2[YYN] -- Number of symbols composing right hand side of rule YYN.  */
static const yytype_uint8 yyr2[] =
{
       0,     2,     1,     4,     2,     0,     3,     3,     5,     3,
       1,     3,     1,     2,     1,     0,     4,     3,     1,     1,
       1,     1,     1,     1,     5,     5,     1,     1,     0,     5,
       3,     3,     1,     2,     2,     1,     0,     3,     1,     1,
       1,     1,     1,     1,     1,     1,     1,     1,     1,     3,
       1,     3,     3,     2,     3,     6,     2,     0,     5,    10,
       2,     0,     5,     2,     1,     0,     1,     1,     2,     1,
       0,     4,     3,     3,     3,     2,     3,     3,     1,     1,
       1,     1,     1,     1,     3,     3,     3,     3,     3,     2,
       2,     3,     3,     1,     5,     1,     1,     1,     0,     3,
       1
};

/* YYDEFACT[STATE-NAME] -- Default reduction number in state STATE-NUM.
   Performed when YYTABLE doesn't specify something else to do.  Zero
   means the default is an error.  */
static const yytype_uint8 yydefact[] =
{
       0,     5,     0,     2,     0,     0,    36,     5,     1,    15,
       0,     0,    12,    10,    26,     0,    28,    66,     0,    70,
       0,     0,     0,     0,    65,    67,     0,     0,    35,    38,
      39,     0,    40,    41,    42,    43,    44,    45,    46,    47,
      48,    50,     4,     0,     0,    13,    14,     6,     0,     0,
       0,     7,    15,     0,    27,     0,    32,     0,     0,    68,
      69,    96,    95,     0,     0,     0,     0,    93,     0,     0,
       0,     0,    53,     0,     0,    63,    64,     0,     3,     0,
       0,     0,     0,     0,     0,    15,     0,    19,    23,    18,
      21,    20,     9,    22,    11,    33,     0,     0,     0,     0,
       0,     0,     0,    70,     0,     0,    89,    75,    90,     0,
       0,     0,     0,     0,    78,    81,    82,    79,    80,     0,
       0,     0,    83,     0,     0,     0,    54,    52,     0,     0,
      37,    49,    51,     0,    17,     0,     0,     5,     0,    30,
      31,     0,    72,     0,    98,    76,    92,    74,    57,    73,
      87,    91,    85,    88,    86,    84,    77,   100,     0,     0,
      24,    16,     8,    36,    25,     0,     0,    71,     0,    97,
       0,     0,     0,    62,    58,    34,    29,    61,    94,    56,
      55,    99,     0,     0,    60,    36,     0,    59
};

/* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int16 yydefgoto[] =
{
      -1,     2,     3,     6,     7,    10,    11,    12,    45,    46,
      92,    93,    15,    16,    53,    54,    55,    56,   164,    27,
      28,    29,    30,    31,    32,    33,    34,   171,    35,    36,
     183,    37,    38,    75,    39,    40,    67,    59,    60,    68,
     124,    69,   168,   158
};

/* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
#define YYPACT_NINF -107
static const yytype_int16 yypact[] =
{
     -13,    65,    25,  -107,    -9,    45,    90,    65,  -107,     6,
      13,    71,    64,  -107,  -107,    69,    63,  -107,    66,   -10,
     210,    70,    26,    83,   226,  -107,   210,   103,    49,  -107,
    -107,   107,  -107,  -107,  -107,  -107,  -107,  -107,  -107,  -107,
    -107,   108,  -107,    81,   104,  -107,   106,  -107,   112,    47,
     112,  -107,   111,    93,    92,   100,  -107,   135,   226,  -107,
     123,  -107,  -107,   212,   226,   210,   226,  -107,    17,   191,
     146,   226,  -107,   226,   228,  -107,   136,    27,  -107,    90,
     226,    66,    -9,   114,   127,   111,   149,  -107,   151,  -107,
    -107,  -107,  -107,  -107,  -107,  -107,    47,    63,    47,    63,
     226,    78,   226,     9,    16,   153,   110,  -107,   110,   210,
      90,   210,   226,   226,  -107,  -107,  -107,  -107,  -107,   226,
     226,   226,  -107,   226,   226,   226,   136,   136,   161,    90,
    -107,   136,  -107,    15,  -107,   119,    47,    65,   121,  -107,
    -107,    -3,  -107,   116,   226,  -107,  -107,  -107,    11,  -107,
     147,   162,   110,   147,   147,   110,   136,   136,    12,    20,
    -107,  -107,  -107,    90,  -107,    47,   226,  -107,   132,   170,
      90,   166,   226,  -107,  -107,  -107,  -107,     7,  -107,    49,
    -107,   136,   226,   173,   136,    90,   169,  -107
};

/* YYPGOTO[NTERM-NUM].  */
static const yytype_int16 yypgoto[] =
{
    -107,  -107,  -107,    -5,  -107,   105,    18,  -107,   137,  -107,
     -87,    -1,  -107,  -107,  -107,  -107,    91,    95,  -107,  -101,
    -106,   118,  -107,   120,  -107,  -107,  -107,  -107,  -107,  -107,
    -107,  -107,  -107,  -107,  -107,  -107,    -6,  -107,  -107,   -18,
    -107,   -23,  -107,    51
};

/* YYTABLE[YYPACT[STATE-NUM]].  What to do in state STATE-NUM.  If
   positive, shift that token.  If negative, reduce the rule which
   number is the opposite.  If YYTABLE_NINF, syntax error.  */
#define YYTABLE_NINF -1
static const yytype_int16 yytable[] =
{
      41,    76,    42,    13,   148,   112,   113,     1,    77,   137,
      43,   139,    57,     9,   144,   112,   113,    58,   172,   109,
     109,   170,   110,   159,    47,     8,   160,   119,   120,   121,
     109,   174,   129,    44,   123,   101,    58,   119,   120,   121,
     105,   106,    71,   108,   123,   104,   166,   107,   126,   162,
     127,   128,   111,   111,   182,   173,    79,   131,    48,   145,
      48,    87,   175,   111,   179,    79,    86,    14,    94,    88,
      50,    89,     4,    41,    72,    41,    49,   141,   176,   143,
      51,    13,    90,     5,   186,    52,   112,   113,    19,   150,
     151,   147,    70,   149,    79,    91,   152,   153,   154,    73,
     155,   156,   157,    17,    41,    98,    99,    18,   119,   120,
     121,    80,    19,    20,    78,   123,    21,    81,   112,   113,
      82,   157,   142,    41,   112,   113,   165,    99,    22,    83,
      23,    24,   163,    84,    85,    96,    25,    97,    44,   100,
      26,   120,   121,   177,   112,   113,   119,   120,   121,   181,
     102,   125,   135,   123,   136,    43,   113,    41,   134,   184,
     167,   112,   113,   161,    41,   114,   119,   120,   121,   112,
     113,    -1,   115,   123,   116,   178,   172,   180,   185,    41,
     187,   117,   118,   119,   120,   121,   122,   133,   138,    95,
     123,   119,   120,   121,   140,   169,   146,   130,   123,   112,
     113,   132,     0,   114,   146,     0,     0,     0,     0,     0,
     115,     0,   116,     0,     0,     0,     0,     0,     0,   117,
     118,   119,   120,   121,   122,    61,     0,    61,   123,     0,
       0,     0,    19,     0,   103,    62,    63,    62,    63,     0,
      64,    61,    64,    61,    65,     0,    65,    66,    19,    66,
     103,    62,    74,    62,    74,     0,    64,     0,    64,     0,
       0,     0,     0,    66,     0,    66
};

#define yypact_value_is_default(Yystate) \
  (!!((Yystate) == (-107)))

#define yytable_value_is_error(Yytable_value) \
  (!!((Yytable_value) == (-1)))

static const yytype_int16 yycheck[] =
{
       6,    24,     7,     4,   110,     8,     9,    20,    26,    96,
       4,    98,    18,    22,     5,     8,     9,    27,     6,     3,
       3,    10,     5,   129,    11,     0,    11,    30,    31,    32,
       3,    11,     5,    27,    37,    58,    27,    30,    31,    32,
      63,    64,    16,    66,    37,    63,    49,    65,    71,   136,
      73,    74,    36,    36,    47,    43,    45,    80,    45,    43,
      45,    14,   163,    36,   170,    45,    48,    22,    50,    22,
       6,    24,     7,    79,    48,    81,     5,   100,   165,   102,
      11,    82,    35,    18,   185,    22,     8,     9,    22,   112,
     113,   109,    22,   111,    45,    48,   119,   120,   121,    16,
     123,   124,   125,    13,   110,     5,     6,    17,    30,    31,
      32,     4,    22,    23,    11,    37,    26,     9,     8,     9,
      39,   144,    44,   129,     8,     9,     5,     6,    38,    25,
      40,    41,   137,    27,    22,    42,    46,    45,    27,     4,
      50,    31,    32,   166,     8,     9,    30,    31,    32,   172,
      27,     5,    25,    37,     5,     4,     9,   163,    44,   182,
      44,     8,     9,    44,   170,    12,    30,    31,    32,     8,
       9,     9,    19,    37,    21,    43,     6,    11,     5,   185,
      11,    28,    29,    30,    31,    32,    33,    82,    97,    52,
      37,    30,    31,    32,    99,   144,    43,    79,    37,     8,
       9,    81,    -1,    12,    43,    -1,    -1,    -1,    -1,    -1,
      19,    -1,    21,    -1,    -1,    -1,    -1,    -1,    -1,    28,
      29,    30,    31,    32,    33,    15,    -1,    15,    37,    -1,
      -1,    -1,    22,    -1,    22,    25,    26,    25,    26,    -1,
      30,    15,    30,    15,    34,    -1,    34,    37,    22,    37,
      22,    25,    26,    25,    26,    -1,    30,    -1,    30,    -1,
      -1,    -1,    -1,    37,    -1,    37
};

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
static const yytype_uint8 yystos[] =
{
       0,    20,    55,    56,     7,    18,    57,    58,     0,    22,
      59,    60,    61,    65,    22,    66,    67,    13,    17,    22,
      23,    26,    38,    40,    41,    46,    50,    73,    74,    75,
      76,    77,    78,    79,    80,    82,    83,    85,    86,    88,
      89,    90,    57,     4,    27,    62,    63,    11,    45,     5,
       6,    11,    22,    68,    69,    70,    71,    90,    27,    91,
      92,    15,    25,    26,    30,    34,    37,    90,    93,    95,
      22,    16,    48,    16,    26,    87,    95,    93,    11,    45,
       4,     9,    39,    25,    27,    22,    60,    14,    22,    24,
      35,    48,    64,    65,    60,    62,    42,    45,     5,     6,
       4,    95,    27,    22,    93,    95,    95,    93,    95,     3,
       5,    36,     8,     9,    12,    19,    21,    28,    29,    30,
      31,    32,    33,    37,    94,     5,    95,    95,    95,     5,
      75,    95,    77,    59,    44,    25,     5,    64,    70,    64,
      71,    95,    44,    95,     5,    43,    43,    93,    74,    93,
      95,    95,    95,    95,    95,    95,    95,    95,    97,    74,
      11,    44,    64,    57,    72,     5,    49,    44,    96,    97,
      10,    81,     6,    43,    11,    73,    64,    95,    43,    74,
      11,    95,    47,    84,    95,     5,    73,    11
};

#define yyerrok		(yyerrstatus = 0)
#define yyclearin	(yychar = YYEMPTY)
#define YYEMPTY		(-2)
#define YYEOF		0

#define YYACCEPT	goto yyacceptlab
#define YYABORT		goto yyabortlab
#define YYERROR		goto yyerrorlab


/* Like YYERROR except do call yyerror.  This remains here temporarily
   to ease the transition to the new meaning of YYERROR, for GCC.
   Once GCC version 2 has supplanted version 1, this can go.  However,
   YYFAIL appears to be in use.  Nevertheless, it is formally deprecated
   in Bison 2.4.2's NEWS entry, where a plan to phase it out is
   discussed.  */

#define YYFAIL		goto yyerrlab
#if defined YYFAIL
  /* This is here to suppress warnings from the GCC cpp's
     -Wunused-macros.  Normally we don't worry about that warning, but
     some users do, and we want to make it easy for users to remove
     YYFAIL uses, which will produce warnings from Bison 2.5.  */
#endif

#define YYRECOVERING()  (!!yyerrstatus)

#define YYBACKUP(Token, Value)                                  \
do                                                              \
  if (yychar == YYEMPTY)                                        \
    {                                                           \
      yychar = (Token);                                         \
      yylval = (Value);                                         \
      YYPOPSTACK (yylen);                                       \
      yystate = *yyssp;                                         \
      goto yybackup;                                            \
    }                                                           \
  else                                                          \
    {                                                           \
      yyerror (YY_("syntax error: cannot back up")); \
      YYERROR;							\
    }								\
while (YYID (0))

/* Error token number */
#define YYTERROR	1
#define YYERRCODE	256


/* YYLLOC_DEFAULT -- Set CURRENT to span from RHS[1] to RHS[N].
   If N is 0, then set CURRENT to the empty location which ends
   the previous symbol: RHS[0] (always defined).  */

#ifndef YYLLOC_DEFAULT
# define YYLLOC_DEFAULT(Current, Rhs, N)                                \
    do                                                                  \
      if (YYID (N))                                                     \
        {                                                               \
          (Current).first_line   = YYRHSLOC (Rhs, 1).first_line;        \
          (Current).first_column = YYRHSLOC (Rhs, 1).first_column;      \
          (Current).last_line    = YYRHSLOC (Rhs, N).last_line;         \
          (Current).last_column  = YYRHSLOC (Rhs, N).last_column;       \
        }                                                               \
      else                                                              \
        {                                                               \
          (Current).first_line   = (Current).last_line   =              \
            YYRHSLOC (Rhs, 0).last_line;                                \
          (Current).first_column = (Current).last_column =              \
            YYRHSLOC (Rhs, 0).last_column;                              \
        }                                                               \
    while (YYID (0))
#endif

#define YYRHSLOC(Rhs, K) ((Rhs)[K])


/* YY_LOCATION_PRINT -- Print the location on the stream.
   This macro was not mandated originally: define only if we know
   we won't break user code: when these are the locations we know.  */

#ifndef __attribute__
/* This feature is available in gcc versions 2.5 and later.  */
# if (! defined __GNUC__ || __GNUC__ < 2 \
      || (__GNUC__ == 2 && __GNUC_MINOR__ < 5))
#  define __attribute__(Spec) /* empty */
# endif
#endif

#ifndef YY_LOCATION_PRINT
# if defined YYLTYPE_IS_TRIVIAL && YYLTYPE_IS_TRIVIAL

/* Print *YYLOCP on YYO.  Private, do not rely on its existence. */

__attribute__((__unused__))
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static unsigned
yy_location_print_ (FILE *yyo, YYLTYPE const * const yylocp)
#else
static unsigned
yy_location_print_ (yyo, yylocp)
    FILE *yyo;
    YYLTYPE const * const yylocp;
#endif
{
  unsigned res = 0;
  int end_col = 0 != yylocp->last_column ? yylocp->last_column - 1 : 0;
  if (0 <= yylocp->first_line)
    {
      res += fprintf (yyo, "%d", yylocp->first_line);
      if (0 <= yylocp->first_column)
        res += fprintf (yyo, ".%d", yylocp->first_column);
    }
  if (0 <= yylocp->last_line)
    {
      if (yylocp->first_line < yylocp->last_line)
        {
          res += fprintf (yyo, "-%d", yylocp->last_line);
          if (0 <= end_col)
            res += fprintf (yyo, ".%d", end_col);
        }
      else if (0 <= end_col && yylocp->first_column < end_col)
        res += fprintf (yyo, "-%d", end_col);
    }
  return res;
 }

#  define YY_LOCATION_PRINT(File, Loc)          \
  yy_location_print_ (File, &(Loc))

# else
#  define YY_LOCATION_PRINT(File, Loc) ((void) 0)
# endif
#endif


/* YYLEX -- calling `yylex' with the right arguments.  */
#ifdef YYLEX_PARAM
# define YYLEX yylex (YYLEX_PARAM)
#else
# define YYLEX yylex ()
#endif

/* Enable debugging if requested.  */
#if YYDEBUG

# ifndef YYFPRINTF
#  include <stdio.h> /* INFRINGES ON USER NAME SPACE */
#  define YYFPRINTF fprintf
# endif

# define YYDPRINTF(Args)			\
do {						\
  if (yydebug)					\
    YYFPRINTF Args;				\
} while (YYID (0))

# define YY_SYMBOL_PRINT(Title, Type, Value, Location)			  \
do {									  \
  if (yydebug)								  \
    {									  \
      YYFPRINTF (stderr, "%s ", Title);					  \
      yy_symbol_print (stderr,						  \
		  Type, Value, Location); \
      YYFPRINTF (stderr, "\n");						  \
    }									  \
} while (YYID (0))


/*--------------------------------.
| Print this symbol on YYOUTPUT.  |
`--------------------------------*/

/*ARGSUSED*/
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_symbol_value_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep, YYLTYPE const * const yylocationp)
#else
static void
yy_symbol_value_print (yyoutput, yytype, yyvaluep, yylocationp)
    FILE *yyoutput;
    int yytype;
    YYSTYPE const * const yyvaluep;
    YYLTYPE const * const yylocationp;
#endif
{
  FILE *yyo = yyoutput;
  YYUSE (yyo);
  if (!yyvaluep)
    return;
  YYUSE (yylocationp);
# ifdef YYPRINT
  if (yytype < YYNTOKENS)
    YYPRINT (yyoutput, yytoknum[yytype], *yyvaluep);
# else
  YYUSE (yyoutput);
# endif
  switch (yytype)
    {
      default:
        break;
    }
}


/*--------------------------------.
| Print this symbol on YYOUTPUT.  |
`--------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_symbol_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep, YYLTYPE const * const yylocationp)
#else
static void
yy_symbol_print (yyoutput, yytype, yyvaluep, yylocationp)
    FILE *yyoutput;
    int yytype;
    YYSTYPE const * const yyvaluep;
    YYLTYPE const * const yylocationp;
#endif
{
  if (yytype < YYNTOKENS)
    YYFPRINTF (yyoutput, "token %s (", yytname[yytype]);
  else
    YYFPRINTF (yyoutput, "nterm %s (", yytname[yytype]);

  YY_LOCATION_PRINT (yyoutput, *yylocationp);
  YYFPRINTF (yyoutput, ": ");
  yy_symbol_value_print (yyoutput, yytype, yyvaluep, yylocationp);
  YYFPRINTF (yyoutput, ")");
}

/*------------------------------------------------------------------.
| yy_stack_print -- Print the state stack from its BOTTOM up to its |
| TOP (included).                                                   |
`------------------------------------------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_stack_print (yytype_int16 *yybottom, yytype_int16 *yytop)
#else
static void
yy_stack_print (yybottom, yytop)
    yytype_int16 *yybottom;
    yytype_int16 *yytop;
#endif
{
  YYFPRINTF (stderr, "Stack now");
  for (; yybottom <= yytop; yybottom++)
    {
      int yybot = *yybottom;
      YYFPRINTF (stderr, " %d", yybot);
    }
  YYFPRINTF (stderr, "\n");
}

# define YY_STACK_PRINT(Bottom, Top)				\
do {								\
  if (yydebug)							\
    yy_stack_print ((Bottom), (Top));				\
} while (YYID (0))


/*------------------------------------------------.
| Report that the YYRULE is going to be reduced.  |
`------------------------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_reduce_print (YYSTYPE *yyvsp, YYLTYPE *yylsp, int yyrule)
#else
static void
yy_reduce_print (yyvsp, yylsp, yyrule)
    YYSTYPE *yyvsp;
    YYLTYPE *yylsp;
    int yyrule;
#endif
{
  int yynrhs = yyr2[yyrule];
  int yyi;
  unsigned long int yylno = yyrline[yyrule];
  YYFPRINTF (stderr, "Reducing stack by rule %d (line %lu):\n",
	     yyrule - 1, yylno);
  /* The symbols being reduced.  */
  for (yyi = 0; yyi < yynrhs; yyi++)
    {
      YYFPRINTF (stderr, "   $%d = ", yyi + 1);
      yy_symbol_print (stderr, yyrhs[yyprhs[yyrule] + yyi],
		       &(yyvsp[(yyi + 1) - (yynrhs)])
		       , &(yylsp[(yyi + 1) - (yynrhs)])		       );
      YYFPRINTF (stderr, "\n");
    }
}

# define YY_REDUCE_PRINT(Rule)		\
do {					\
  if (yydebug)				\
    yy_reduce_print (yyvsp, yylsp, Rule); \
} while (YYID (0))

/* Nonzero means print parse trace.  It is left uninitialized so that
   multiple parsers can coexist.  */
int yydebug;
#else /* !YYDEBUG */
# define YYDPRINTF(Args)
# define YY_SYMBOL_PRINT(Title, Type, Value, Location)
# define YY_STACK_PRINT(Bottom, Top)
# define YY_REDUCE_PRINT(Rule)
#endif /* !YYDEBUG */


/* YYINITDEPTH -- initial size of the parser's stacks.  */
#ifndef	YYINITDEPTH
# define YYINITDEPTH 200
#endif

/* YYMAXDEPTH -- maximum size the stacks can grow to (effective only
   if the built-in stack extension method is used).

   Do not make this value too large; the results are undefined if
   YYSTACK_ALLOC_MAXIMUM < YYSTACK_BYTES (YYMAXDEPTH)
   evaluated with infinite-precision integer arithmetic.  */

#ifndef YYMAXDEPTH
# define YYMAXDEPTH 10000
#endif


#if YYERROR_VERBOSE

# ifndef yystrlen
#  if defined __GLIBC__ && defined _STRING_H
#   define yystrlen strlen
#  else
/* Return the length of YYSTR.  */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static YYSIZE_T
yystrlen (const char *yystr)
#else
static YYSIZE_T
yystrlen (yystr)
    const char *yystr;
#endif
{
  YYSIZE_T yylen;
  for (yylen = 0; yystr[yylen]; yylen++)
    continue;
  return yylen;
}
#  endif
# endif

# ifndef yystpcpy
#  if defined __GLIBC__ && defined _STRING_H && defined _GNU_SOURCE
#   define yystpcpy stpcpy
#  else
/* Copy YYSRC to YYDEST, returning the address of the terminating '\0' in
   YYDEST.  */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static char *
yystpcpy (char *yydest, const char *yysrc)
#else
static char *
yystpcpy (yydest, yysrc)
    char *yydest;
    const char *yysrc;
#endif
{
  char *yyd = yydest;
  const char *yys = yysrc;

  while ((*yyd++ = *yys++) != '\0')
    continue;

  return yyd - 1;
}
#  endif
# endif

# ifndef yytnamerr
/* Copy to YYRES the contents of YYSTR after stripping away unnecessary
   quotes and backslashes, so that it's suitable for yyerror.  The
   heuristic is that double-quoting is unnecessary unless the string
   contains an apostrophe, a comma, or backslash (other than
   backslash-backslash).  YYSTR is taken from yytname.  If YYRES is
   null, do not copy; instead, return the length of what the result
   would have been.  */
static YYSIZE_T
yytnamerr (char *yyres, const char *yystr)
{
  if (*yystr == '"')
    {
      YYSIZE_T yyn = 0;
      char const *yyp = yystr;

      for (;;)
	switch (*++yyp)
	  {
	  case '\'':
	  case ',':
	    goto do_not_strip_quotes;

	  case '\\':
	    if (*++yyp != '\\')
	      goto do_not_strip_quotes;
	    /* Fall through.  */
	  default:
	    if (yyres)
	      yyres[yyn] = *yyp;
	    yyn++;
	    break;

	  case '"':
	    if (yyres)
	      yyres[yyn] = '\0';
	    return yyn;
	  }
    do_not_strip_quotes: ;
    }

  if (! yyres)
    return yystrlen (yystr);

  return yystpcpy (yyres, yystr) - yyres;
}
# endif

/* Copy into *YYMSG, which is of size *YYMSG_ALLOC, an error message
   about the unexpected token YYTOKEN for the state stack whose top is
   YYSSP.

   Return 0 if *YYMSG was successfully written.  Return 1 if *YYMSG is
   not large enough to hold the message.  In that case, also set
   *YYMSG_ALLOC to the required number of bytes.  Return 2 if the
   required number of bytes is too large to store.  */
static int
yysyntax_error (YYSIZE_T *yymsg_alloc, char **yymsg,
                yytype_int16 *yyssp, int yytoken)
{
  YYSIZE_T yysize0 = yytnamerr (YY_NULL, yytname[yytoken]);
  YYSIZE_T yysize = yysize0;
  enum { YYERROR_VERBOSE_ARGS_MAXIMUM = 5 };
  /* Internationalized format string. */
  const char *yyformat = YY_NULL;
  /* Arguments of yyformat. */
  char const *yyarg[YYERROR_VERBOSE_ARGS_MAXIMUM];
  /* Number of reported tokens (one for the "unexpected", one per
     "expected"). */
  int yycount = 0;

  /* There are many possibilities here to consider:
     - Assume YYFAIL is not used.  It's too flawed to consider.  See
       <http://lists.gnu.org/archive/html/bison-patches/2009-12/msg00024.html>
       for details.  YYERROR is fine as it does not invoke this
       function.
     - If this state is a consistent state with a default action, then
       the only way this function was invoked is if the default action
       is an error action.  In that case, don't check for expected
       tokens because there are none.
     - The only way there can be no lookahead present (in yychar) is if
       this state is a consistent state with a default action.  Thus,
       detecting the absence of a lookahead is sufficient to determine
       that there is no unexpected or expected token to report.  In that
       case, just report a simple "syntax error".
     - Don't assume there isn't a lookahead just because this state is a
       consistent state with a default action.  There might have been a
       previous inconsistent state, consistent state with a non-default
       action, or user semantic action that manipulated yychar.
     - Of course, the expected token list depends on states to have
       correct lookahead information, and it depends on the parser not
       to perform extra reductions after fetching a lookahead from the
       scanner and before detecting a syntax error.  Thus, state merging
       (from LALR or IELR) and default reductions corrupt the expected
       token list.  However, the list is correct for canonical LR with
       one exception: it will still contain any token that will not be
       accepted due to an error action in a later state.
  */
  if (yytoken != YYEMPTY)
    {
      int yyn = yypact[*yyssp];
      yyarg[yycount++] = yytname[yytoken];
      if (!yypact_value_is_default (yyn))
        {
          /* Start YYX at -YYN if negative to avoid negative indexes in
             YYCHECK.  In other words, skip the first -YYN actions for
             this state because they are default actions.  */
          int yyxbegin = yyn < 0 ? -yyn : 0;
          /* Stay within bounds of both yycheck and yytname.  */
          int yychecklim = YYLAST - yyn + 1;
          int yyxend = yychecklim < YYNTOKENS ? yychecklim : YYNTOKENS;
          int yyx;

          for (yyx = yyxbegin; yyx < yyxend; ++yyx)
            if (yycheck[yyx + yyn] == yyx && yyx != YYTERROR
                && !yytable_value_is_error (yytable[yyx + yyn]))
              {
                if (yycount == YYERROR_VERBOSE_ARGS_MAXIMUM)
                  {
                    yycount = 1;
                    yysize = yysize0;
                    break;
                  }
                yyarg[yycount++] = yytname[yyx];
                {
                  YYSIZE_T yysize1 = yysize + yytnamerr (YY_NULL, yytname[yyx]);
                  if (! (yysize <= yysize1
                         && yysize1 <= YYSTACK_ALLOC_MAXIMUM))
                    return 2;
                  yysize = yysize1;
                }
              }
        }
    }

  switch (yycount)
    {
# define YYCASE_(N, S)                      \
      case N:                               \
        yyformat = S;                       \
      break
      YYCASE_(0, YY_("syntax error"));
      YYCASE_(1, YY_("syntax error, unexpected %s"));
      YYCASE_(2, YY_("syntax error, unexpected %s, expecting %s"));
      YYCASE_(3, YY_("syntax error, unexpected %s, expecting %s or %s"));
      YYCASE_(4, YY_("syntax error, unexpected %s, expecting %s or %s or %s"));
      YYCASE_(5, YY_("syntax error, unexpected %s, expecting %s or %s or %s or %s"));
# undef YYCASE_
    }

  {
    YYSIZE_T yysize1 = yysize + yystrlen (yyformat);
    if (! (yysize <= yysize1 && yysize1 <= YYSTACK_ALLOC_MAXIMUM))
      return 2;
    yysize = yysize1;
  }

  if (*yymsg_alloc < yysize)
    {
      *yymsg_alloc = 2 * yysize;
      if (! (yysize <= *yymsg_alloc
             && *yymsg_alloc <= YYSTACK_ALLOC_MAXIMUM))
        *yymsg_alloc = YYSTACK_ALLOC_MAXIMUM;
      return 1;
    }

  /* Avoid sprintf, as that infringes on the user's name space.
     Don't have undefined behavior even if the translation
     produced a string with the wrong number of "%s"s.  */
  {
    char *yyp = *yymsg;
    int yyi = 0;
    while ((*yyp = *yyformat) != '\0')
      if (*yyp == '%' && yyformat[1] == 's' && yyi < yycount)
        {
          yyp += yytnamerr (yyp, yyarg[yyi++]);
          yyformat += 2;
        }
      else
        {
          yyp++;
          yyformat++;
        }
  }
  return 0;
}
#endif /* YYERROR_VERBOSE */

/*-----------------------------------------------.
| Release the memory associated to this symbol.  |
`-----------------------------------------------*/

/*ARGSUSED*/
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yydestruct (const char *yymsg, int yytype, YYSTYPE *yyvaluep, YYLTYPE *yylocationp)
#else
static void
yydestruct (yymsg, yytype, yyvaluep, yylocationp)
    const char *yymsg;
    int yytype;
    YYSTYPE *yyvaluep;
    YYLTYPE *yylocationp;
#endif
{
  YYUSE (yyvaluep);
  YYUSE (yylocationp);

  if (!yymsg)
    yymsg = "Deleting";
  YY_SYMBOL_PRINT (yymsg, yytype, yyvaluep, yylocationp);

  switch (yytype)
    {

      default:
        break;
    }
}




/* The lookahead symbol.  */
int yychar;


#ifndef YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
# define YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
# define YY_IGNORE_MAYBE_UNINITIALIZED_END
#endif
#ifndef YY_INITIAL_VALUE
# define YY_INITIAL_VALUE(Value) /* Nothing. */
#endif

/* The semantic value of the lookahead symbol.  */
YYSTYPE yylval YY_INITIAL_VALUE(yyval_default);

/* Location data for the lookahead symbol.  */
YYLTYPE yylloc
# if defined YYLTYPE_IS_TRIVIAL && YYLTYPE_IS_TRIVIAL
  = { 1, 1, 1, 1 }
# endif
;


/* Number of syntax errors so far.  */
int yynerrs;


/*----------.
| yyparse.  |
`----------*/

#ifdef YYPARSE_PARAM
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
int
yyparse (void *YYPARSE_PARAM)
#else
int
yyparse (YYPARSE_PARAM)
    void *YYPARSE_PARAM;
#endif
#else /* ! YYPARSE_PARAM */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
int
yyparse (void)
#else
int
yyparse ()

#endif
#endif
{
    int yystate;
    /* Number of tokens to shift before error messages enabled.  */
    int yyerrstatus;

    /* The stacks and their tools:
       `yyss': related to states.
       `yyvs': related to semantic values.
       `yyls': related to locations.

       Refer to the stacks through separate pointers, to allow yyoverflow
       to reallocate them elsewhere.  */

    /* The state stack.  */
    yytype_int16 yyssa[YYINITDEPTH];
    yytype_int16 *yyss;
    yytype_int16 *yyssp;

    /* The semantic value stack.  */
    YYSTYPE yyvsa[YYINITDEPTH];
    YYSTYPE *yyvs;
    YYSTYPE *yyvsp;

    /* The location stack.  */
    YYLTYPE yylsa[YYINITDEPTH];
    YYLTYPE *yyls;
    YYLTYPE *yylsp;

    /* The locations where the error started and ended.  */
    YYLTYPE yyerror_range[3];

    YYSIZE_T yystacksize;

  int yyn;
  int yyresult;
  /* Lookahead token as an internal (translated) token number.  */
  int yytoken = 0;
  /* The variables used to return semantic value and location from the
     action routines.  */
  YYSTYPE yyval;
  YYLTYPE yyloc;

#if YYERROR_VERBOSE
  /* Buffer for error messages, and its allocated size.  */
  char yymsgbuf[128];
  char *yymsg = yymsgbuf;
  YYSIZE_T yymsg_alloc = sizeof yymsgbuf;
#endif

#define YYPOPSTACK(N)   (yyvsp -= (N), yyssp -= (N), yylsp -= (N))

  /* The number of symbols on the RHS of the reduced rule.
     Keep to zero when no symbol should be popped.  */
  int yylen = 0;

  yyssp = yyss = yyssa;
  yyvsp = yyvs = yyvsa;
  yylsp = yyls = yylsa;
  yystacksize = YYINITDEPTH;

  YYDPRINTF ((stderr, "Starting parse\n"));

  yystate = 0;
  yyerrstatus = 0;
  yynerrs = 0;
  yychar = YYEMPTY; /* Cause a token to be read.  */
  yylsp[0] = yylloc;
  goto yysetstate;

/*------------------------------------------------------------.
| yynewstate -- Push a new state, which is found in yystate.  |
`------------------------------------------------------------*/
 yynewstate:
  /* In all cases, when you get here, the value and location stacks
     have just been pushed.  So pushing a state here evens the stacks.  */
  yyssp++;

 yysetstate:
  *yyssp = yystate;

  if (yyss + yystacksize - 1 <= yyssp)
    {
      /* Get the current used size of the three stacks, in elements.  */
      YYSIZE_T yysize = yyssp - yyss + 1;

#ifdef yyoverflow
      {
	/* Give user a chance to reallocate the stack.  Use copies of
	   these so that the &'s don't force the real ones into
	   memory.  */
	YYSTYPE *yyvs1 = yyvs;
	yytype_int16 *yyss1 = yyss;
	YYLTYPE *yyls1 = yyls;

	/* Each stack pointer address is followed by the size of the
	   data in use in that stack, in bytes.  This used to be a
	   conditional around just the two extra args, but that might
	   be undefined if yyoverflow is a macro.  */
	yyoverflow (YY_("memory exhausted"),
		    &yyss1, yysize * sizeof (*yyssp),
		    &yyvs1, yysize * sizeof (*yyvsp),
		    &yyls1, yysize * sizeof (*yylsp),
		    &yystacksize);

	yyls = yyls1;
	yyss = yyss1;
	yyvs = yyvs1;
      }
#else /* no yyoverflow */
# ifndef YYSTACK_RELOCATE
      goto yyexhaustedlab;
# else
      /* Extend the stack our own way.  */
      if (YYMAXDEPTH <= yystacksize)
	goto yyexhaustedlab;
      yystacksize *= 2;
      if (YYMAXDEPTH < yystacksize)
	yystacksize = YYMAXDEPTH;

      {
	yytype_int16 *yyss1 = yyss;
	union yyalloc *yyptr =
	  (union yyalloc *) YYSTACK_ALLOC (YYSTACK_BYTES (yystacksize));
	if (! yyptr)
	  goto yyexhaustedlab;
	YYSTACK_RELOCATE (yyss_alloc, yyss);
	YYSTACK_RELOCATE (yyvs_alloc, yyvs);
	YYSTACK_RELOCATE (yyls_alloc, yyls);
#  undef YYSTACK_RELOCATE
	if (yyss1 != yyssa)
	  YYSTACK_FREE (yyss1);
      }
# endif
#endif /* no yyoverflow */

      yyssp = yyss + yysize - 1;
      yyvsp = yyvs + yysize - 1;
      yylsp = yyls + yysize - 1;

      YYDPRINTF ((stderr, "Stack size increased to %lu\n",
		  (unsigned long int) yystacksize));

      if (yyss + yystacksize - 1 <= yyssp)
	YYABORT;
    }

  YYDPRINTF ((stderr, "Entering state %d\n", yystate));

  if (yystate == YYFINAL)
    YYACCEPT;

  goto yybackup;

/*-----------.
| yybackup.  |
`-----------*/
yybackup:

  /* Do appropriate processing given the current state.  Read a
     lookahead token if we need one and don't already have one.  */

  /* First try to decide what to do without reference to lookahead token.  */
  yyn = yypact[yystate];
  if (yypact_value_is_default (yyn))
    goto yydefault;

  /* Not known => get a lookahead token if don't already have one.  */

  /* YYCHAR is either YYEMPTY or YYEOF or a valid lookahead symbol.  */
  if (yychar == YYEMPTY)
    {
      YYDPRINTF ((stderr, "Reading a token: "));
      yychar = YYLEX;
    }

  if (yychar <= YYEOF)
    {
      yychar = yytoken = YYEOF;
      YYDPRINTF ((stderr, "Now at end of input.\n"));
    }
  else
    {
      yytoken = YYTRANSLATE (yychar);
      YY_SYMBOL_PRINT ("Next token is", yytoken, &yylval, &yylloc);
    }

  /* If the proper action on seeing token YYTOKEN is to reduce or to
     detect an error, take that action.  */
  yyn += yytoken;
  if (yyn < 0 || YYLAST < yyn || yycheck[yyn] != yytoken)
    goto yydefault;
  yyn = yytable[yyn];
  if (yyn <= 0)
    {
      if (yytable_value_is_error (yyn))
        goto yyerrlab;
      yyn = -yyn;
      goto yyreduce;
    }

  /* Count tokens shifted since error; after three, turn off error
     status.  */
  if (yyerrstatus)
    yyerrstatus--;

  /* Shift the lookahead token.  */
  YY_SYMBOL_PRINT ("Shifting", yytoken, &yylval, &yylloc);

  /* Discard the shifted token.  */
  yychar = YYEMPTY;

  yystate = yyn;
  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  *++yyvsp = yylval;
  YY_IGNORE_MAYBE_UNINITIALIZED_END
  *++yylsp = yylloc;
  goto yynewstate;


/*-----------------------------------------------------------.
| yydefault -- do the default action for the current state.  |
`-----------------------------------------------------------*/
yydefault:
  yyn = yydefact[yystate];
  if (yyn == 0)
    goto yyerrlab;
  goto yyreduce;


/*-----------------------------.
| yyreduce -- Do a reduction.  |
`-----------------------------*/
yyreduce:
  /* yyn is the number of a rule to reduce with.  */
  yylen = yyr2[yyn];

  /* If YYLEN is nonzero, implement the default value of the action:
     `$$ = $1'.

     Otherwise, the following line sets YYVAL to garbage.
     This behavior is undocumented and Bison
     users should not rely upon it.  Assigning to YYVAL
     unconditionally makes the parser a bit smaller, and it avoids a
     GCC warning that YYVAL may be used uninitialized.  */
  yyval = yyvsp[1-yylen];

  /* Default location.  */
  YYLLOC_DEFAULT (yyloc, (yylsp - yylen), yylen);
  YY_REDUCE_PRINT (yyn);
  switch (yyn)
    {
        case 2:
/* Line 1792 of yacc.c  */
#line 26 "parse.y"
    { 
	printf("Successfully parsed"); 
	return 0; 
}
    break;

  case 3:
/* Line 1792 of yacc.c  */
#line 30 "parse.y"
    {}
    break;

  case 4:
/* Line 1792 of yacc.c  */
#line 32 "parse.y"
    {}
    break;

  case 5:
/* Line 1792 of yacc.c  */
#line 33 "parse.y"
    {}
    break;

  case 6:
/* Line 1792 of yacc.c  */
#line 35 "parse.y"
    {}
    break;

  case 7:
/* Line 1792 of yacc.c  */
#line 36 "parse.y"
    {}
    break;

  case 8:
/* Line 1792 of yacc.c  */
#line 38 "parse.y"
    {}
    break;

  case 9:
/* Line 1792 of yacc.c  */
#line 39 "parse.y"
    {}
    break;

  case 10:
/* Line 1792 of yacc.c  */
#line 40 "parse.y"
    {}
    break;

  case 11:
/* Line 1792 of yacc.c  */
#line 42 "parse.y"
    {}
    break;

  case 12:
/* Line 1792 of yacc.c  */
#line 43 "parse.y"
    {}
    break;

  case 13:
/* Line 1792 of yacc.c  */
#line 45 "parse.y"
    {}
    break;

  case 14:
/* Line 1792 of yacc.c  */
#line 47 "parse.y"
    {}
    break;

  case 15:
/* Line 1792 of yacc.c  */
#line 48 "parse.y"
    {}
    break;

  case 16:
/* Line 1792 of yacc.c  */
#line 50 "parse.y"
    {}
    break;

  case 17:
/* Line 1792 of yacc.c  */
#line 51 "parse.y"
    {}
    break;

  case 18:
/* Line 1792 of yacc.c  */
#line 53 "parse.y"
    {}
    break;

  case 19:
/* Line 1792 of yacc.c  */
#line 54 "parse.y"
    {}
    break;

  case 20:
/* Line 1792 of yacc.c  */
#line 55 "parse.y"
    {}
    break;

  case 21:
/* Line 1792 of yacc.c  */
#line 56 "parse.y"
    {}
    break;

  case 22:
/* Line 1792 of yacc.c  */
#line 57 "parse.y"
    {}
    break;

  case 23:
/* Line 1792 of yacc.c  */
#line 58 "parse.y"
    {}
    break;

  case 24:
/* Line 1792 of yacc.c  */
#line 60 "parse.y"
    {}
    break;

  case 25:
/* Line 1792 of yacc.c  */
#line 62 "parse.y"
    {}
    break;

  case 26:
/* Line 1792 of yacc.c  */
#line 64 "parse.y"
    {}
    break;

  case 27:
/* Line 1792 of yacc.c  */
#line 66 "parse.y"
    {}
    break;

  case 28:
/* Line 1792 of yacc.c  */
#line 67 "parse.y"
    {}
    break;

  case 29:
/* Line 1792 of yacc.c  */
#line 69 "parse.y"
    {}
    break;

  case 30:
/* Line 1792 of yacc.c  */
#line 70 "parse.y"
    {}
    break;

  case 31:
/* Line 1792 of yacc.c  */
#line 72 "parse.y"
    {}
    break;

  case 32:
/* Line 1792 of yacc.c  */
#line 73 "parse.y"
    {}
    break;

  case 33:
/* Line 1792 of yacc.c  */
#line 75 "parse.y"
    {}
    break;

  case 34:
/* Line 1792 of yacc.c  */
#line 77 "parse.y"
    {}
    break;

  case 35:
/* Line 1792 of yacc.c  */
#line 79 "parse.y"
    {}
    break;

  case 36:
/* Line 1792 of yacc.c  */
#line 80 "parse.y"
    {}
    break;

  case 37:
/* Line 1792 of yacc.c  */
#line 82 "parse.y"
    {}
    break;

  case 38:
/* Line 1792 of yacc.c  */
#line 83 "parse.y"
    {}
    break;

  case 39:
/* Line 1792 of yacc.c  */
#line 85 "parse.y"
    {}
    break;

  case 40:
/* Line 1792 of yacc.c  */
#line 86 "parse.y"
    {}
    break;

  case 41:
/* Line 1792 of yacc.c  */
#line 87 "parse.y"
    {}
    break;

  case 42:
/* Line 1792 of yacc.c  */
#line 88 "parse.y"
    {}
    break;

  case 43:
/* Line 1792 of yacc.c  */
#line 89 "parse.y"
    {}
    break;

  case 44:
/* Line 1792 of yacc.c  */
#line 90 "parse.y"
    {}
    break;

  case 45:
/* Line 1792 of yacc.c  */
#line 91 "parse.y"
    {}
    break;

  case 46:
/* Line 1792 of yacc.c  */
#line 92 "parse.y"
    {}
    break;

  case 47:
/* Line 1792 of yacc.c  */
#line 93 "parse.y"
    {}
    break;

  case 48:
/* Line 1792 of yacc.c  */
#line 94 "parse.y"
    {}
    break;

  case 49:
/* Line 1792 of yacc.c  */
#line 96 "parse.y"
    {}
    break;

  case 50:
/* Line 1792 of yacc.c  */
#line 98 "parse.y"
    {}
    break;

  case 51:
/* Line 1792 of yacc.c  */
#line 99 "parse.y"
    {}
    break;

  case 52:
/* Line 1792 of yacc.c  */
#line 101 "parse.y"
    {}
    break;

  case 53:
/* Line 1792 of yacc.c  */
#line 103 "parse.y"
    {}
    break;

  case 54:
/* Line 1792 of yacc.c  */
#line 104 "parse.y"
    {}
    break;

  case 55:
/* Line 1792 of yacc.c  */
#line 106 "parse.y"
    {}
    break;

  case 56:
/* Line 1792 of yacc.c  */
#line 108 "parse.y"
    {}
    break;

  case 57:
/* Line 1792 of yacc.c  */
#line 109 "parse.y"
    {}
    break;

  case 58:
/* Line 1792 of yacc.c  */
#line 111 "parse.y"
    {}
    break;

  case 59:
/* Line 1792 of yacc.c  */
#line 113 "parse.y"
    {}
    break;

  case 60:
/* Line 1792 of yacc.c  */
#line 115 "parse.y"
    {}
    break;

  case 61:
/* Line 1792 of yacc.c  */
#line 116 "parse.y"
    {}
    break;

  case 62:
/* Line 1792 of yacc.c  */
#line 118 "parse.y"
    {}
    break;

  case 63:
/* Line 1792 of yacc.c  */
#line 120 "parse.y"
    {}
    break;

  case 64:
/* Line 1792 of yacc.c  */
#line 122 "parse.y"
    {}
    break;

  case 65:
/* Line 1792 of yacc.c  */
#line 123 "parse.y"
    {}
    break;

  case 66:
/* Line 1792 of yacc.c  */
#line 125 "parse.y"
    {}
    break;

  case 67:
/* Line 1792 of yacc.c  */
#line 127 "parse.y"
    {}
    break;

  case 68:
/* Line 1792 of yacc.c  */
#line 129 "parse.y"
    {}
    break;

  case 69:
/* Line 1792 of yacc.c  */
#line 131 "parse.y"
    {}
    break;

  case 70:
/* Line 1792 of yacc.c  */
#line 132 "parse.y"
    {}
    break;

  case 71:
/* Line 1792 of yacc.c  */
#line 134 "parse.y"
    {}
    break;

  case 72:
/* Line 1792 of yacc.c  */
#line 135 "parse.y"
    {}
    break;

  case 73:
/* Line 1792 of yacc.c  */
#line 137 "parse.y"
    {}
    break;

  case 74:
/* Line 1792 of yacc.c  */
#line 138 "parse.y"
    {}
    break;

  case 75:
/* Line 1792 of yacc.c  */
#line 139 "parse.y"
    {}
    break;

  case 76:
/* Line 1792 of yacc.c  */
#line 140 "parse.y"
    {}
    break;

  case 77:
/* Line 1792 of yacc.c  */
#line 141 "parse.y"
    {}
    break;

  case 78:
/* Line 1792 of yacc.c  */
#line 143 "parse.y"
    {}
    break;

  case 79:
/* Line 1792 of yacc.c  */
#line 144 "parse.y"
    {}
    break;

  case 80:
/* Line 1792 of yacc.c  */
#line 145 "parse.y"
    {}
    break;

  case 81:
/* Line 1792 of yacc.c  */
#line 146 "parse.y"
    {}
    break;

  case 82:
/* Line 1792 of yacc.c  */
#line 147 "parse.y"
    {}
    break;

  case 83:
/* Line 1792 of yacc.c  */
#line 148 "parse.y"
    {}
    break;

  case 84:
/* Line 1792 of yacc.c  */
#line 150 "parse.y"
    {}
    break;

  case 85:
/* Line 1792 of yacc.c  */
#line 151 "parse.y"
    {}
    break;

  case 86:
/* Line 1792 of yacc.c  */
#line 152 "parse.y"
    {}
    break;

  case 87:
/* Line 1792 of yacc.c  */
#line 153 "parse.y"
    {}
    break;

  case 88:
/* Line 1792 of yacc.c  */
#line 154 "parse.y"
    {}
    break;

  case 89:
/* Line 1792 of yacc.c  */
#line 155 "parse.y"
    {}
    break;

  case 90:
/* Line 1792 of yacc.c  */
#line 156 "parse.y"
    {}
    break;

  case 91:
/* Line 1792 of yacc.c  */
#line 157 "parse.y"
    {}
    break;

  case 92:
/* Line 1792 of yacc.c  */
#line 158 "parse.y"
    {}
    break;

  case 93:
/* Line 1792 of yacc.c  */
#line 159 "parse.y"
    {}
    break;

  case 94:
/* Line 1792 of yacc.c  */
#line 160 "parse.y"
    {}
    break;

  case 95:
/* Line 1792 of yacc.c  */
#line 161 "parse.y"
    {}
    break;

  case 96:
/* Line 1792 of yacc.c  */
#line 162 "parse.y"
    {}
    break;

  case 97:
/* Line 1792 of yacc.c  */
#line 164 "parse.y"
    {}
    break;

  case 98:
/* Line 1792 of yacc.c  */
#line 165 "parse.y"
    {}
    break;

  case 99:
/* Line 1792 of yacc.c  */
#line 167 "parse.y"
    {}
    break;

  case 100:
/* Line 1792 of yacc.c  */
#line 168 "parse.y"
    {}
    break;


/* Line 1792 of yacc.c  */
#line 2302 "parse.tab.c"
      default: break;
    }
  /* User semantic actions sometimes alter yychar, and that requires
     that yytoken be updated with the new translation.  We take the
     approach of translating immediately before every use of yytoken.
     One alternative is translating here after every semantic action,
     but that translation would be missed if the semantic action invokes
     YYABORT, YYACCEPT, or YYERROR immediately after altering yychar or
     if it invokes YYBACKUP.  In the case of YYABORT or YYACCEPT, an
     incorrect destructor might then be invoked immediately.  In the
     case of YYERROR or YYBACKUP, subsequent parser actions might lead
     to an incorrect destructor call or verbose syntax error message
     before the lookahead is translated.  */
  YY_SYMBOL_PRINT ("-> $$ =", yyr1[yyn], &yyval, &yyloc);

  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);

  *++yyvsp = yyval;
  *++yylsp = yyloc;

  /* Now `shift' the result of the reduction.  Determine what state
     that goes to, based on the state we popped back to and the rule
     number reduced by.  */

  yyn = yyr1[yyn];

  yystate = yypgoto[yyn - YYNTOKENS] + *yyssp;
  if (0 <= yystate && yystate <= YYLAST && yycheck[yystate] == *yyssp)
    yystate = yytable[yystate];
  else
    yystate = yydefgoto[yyn - YYNTOKENS];

  goto yynewstate;


/*------------------------------------.
| yyerrlab -- here on detecting error |
`------------------------------------*/
yyerrlab:
  /* Make sure we have latest lookahead translation.  See comments at
     user semantic actions for why this is necessary.  */
  yytoken = yychar == YYEMPTY ? YYEMPTY : YYTRANSLATE (yychar);

  /* If not already recovering from an error, report this error.  */
  if (!yyerrstatus)
    {
      ++yynerrs;
#if ! YYERROR_VERBOSE
      yyerror (YY_("syntax error"));
#else
# define YYSYNTAX_ERROR yysyntax_error (&yymsg_alloc, &yymsg, \
                                        yyssp, yytoken)
      {
        char const *yymsgp = YY_("syntax error");
        int yysyntax_error_status;
        yysyntax_error_status = YYSYNTAX_ERROR;
        if (yysyntax_error_status == 0)
          yymsgp = yymsg;
        else if (yysyntax_error_status == 1)
          {
            if (yymsg != yymsgbuf)
              YYSTACK_FREE (yymsg);
            yymsg = (char *) YYSTACK_ALLOC (yymsg_alloc);
            if (!yymsg)
              {
                yymsg = yymsgbuf;
                yymsg_alloc = sizeof yymsgbuf;
                yysyntax_error_status = 2;
              }
            else
              {
                yysyntax_error_status = YYSYNTAX_ERROR;
                yymsgp = yymsg;
              }
          }
        yyerror (yymsgp);
        if (yysyntax_error_status == 2)
          goto yyexhaustedlab;
      }
# undef YYSYNTAX_ERROR
#endif
    }

  yyerror_range[1] = yylloc;

  if (yyerrstatus == 3)
    {
      /* If just tried and failed to reuse lookahead token after an
	 error, discard it.  */

      if (yychar <= YYEOF)
	{
	  /* Return failure if at end of input.  */
	  if (yychar == YYEOF)
	    YYABORT;
	}
      else
	{
	  yydestruct ("Error: discarding",
		      yytoken, &yylval, &yylloc);
	  yychar = YYEMPTY;
	}
    }

  /* Else will try to reuse lookahead token after shifting the error
     token.  */
  goto yyerrlab1;


/*---------------------------------------------------.
| yyerrorlab -- error raised explicitly by YYERROR.  |
`---------------------------------------------------*/
yyerrorlab:

  /* Pacify compilers like GCC when the user code never invokes
     YYERROR and the label yyerrorlab therefore never appears in user
     code.  */
  if (/*CONSTCOND*/ 0)
     goto yyerrorlab;

  yyerror_range[1] = yylsp[1-yylen];
  /* Do not reclaim the symbols of the rule which action triggered
     this YYERROR.  */
  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);
  yystate = *yyssp;
  goto yyerrlab1;


/*-------------------------------------------------------------.
| yyerrlab1 -- common code for both syntax error and YYERROR.  |
`-------------------------------------------------------------*/
yyerrlab1:
  yyerrstatus = 3;	/* Each real token shifted decrements this.  */

  for (;;)
    {
      yyn = yypact[yystate];
      if (!yypact_value_is_default (yyn))
	{
	  yyn += YYTERROR;
	  if (0 <= yyn && yyn <= YYLAST && yycheck[yyn] == YYTERROR)
	    {
	      yyn = yytable[yyn];
	      if (0 < yyn)
		break;
	    }
	}

      /* Pop the current state because it cannot handle the error token.  */
      if (yyssp == yyss)
	YYABORT;

      yyerror_range[1] = *yylsp;
      yydestruct ("Error: popping",
		  yystos[yystate], yyvsp, yylsp);
      YYPOPSTACK (1);
      yystate = *yyssp;
      YY_STACK_PRINT (yyss, yyssp);
    }

  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  *++yyvsp = yylval;
  YY_IGNORE_MAYBE_UNINITIALIZED_END

  yyerror_range[2] = yylloc;
  /* Using YYLLOC is tempting, but would change the location of
     the lookahead.  YYLOC is available though.  */
  YYLLOC_DEFAULT (yyloc, yyerror_range, 2);
  *++yylsp = yyloc;

  /* Shift the error token.  */
  YY_SYMBOL_PRINT ("Shifting", yystos[yyn], yyvsp, yylsp);

  yystate = yyn;
  goto yynewstate;


/*-------------------------------------.
| yyacceptlab -- YYACCEPT comes here.  |
`-------------------------------------*/
yyacceptlab:
  yyresult = 0;
  goto yyreturn;

/*-----------------------------------.
| yyabortlab -- YYABORT comes here.  |
`-----------------------------------*/
yyabortlab:
  yyresult = 1;
  goto yyreturn;

#if !defined yyoverflow || YYERROR_VERBOSE
/*-------------------------------------------------.
| yyexhaustedlab -- memory exhaustion comes here.  |
`-------------------------------------------------*/
yyexhaustedlab:
  yyerror (YY_("memory exhausted"));
  yyresult = 2;
  /* Fall through.  */
#endif

yyreturn:
  if (yychar != YYEMPTY)
    {
      /* Make sure we have latest lookahead translation.  See comments at
         user semantic actions for why this is necessary.  */
      yytoken = YYTRANSLATE (yychar);
      yydestruct ("Cleanup: discarding lookahead",
                  yytoken, &yylval, &yylloc);
    }
  /* Do not reclaim the symbols of the rule which action triggered
     this YYABORT or YYACCEPT.  */
  YYPOPSTACK (yylen);
  YY_STACK_PRINT (yyss, yyssp);
  while (yyssp != yyss)
    {
      yydestruct ("Cleanup: popping",
		  yystos[*yyssp], yyvsp, yylsp);
      YYPOPSTACK (1);
    }
#ifndef yyoverflow
  if (yyss != yyssa)
    YYSTACK_FREE (yyss);
#endif
#if YYERROR_VERBOSE
  if (yymsg != yymsgbuf)
    YYSTACK_FREE (yymsg);
#endif
  /* Make sure YYID is used.  */
  return YYID (yyresult);
}


/* Line 2055 of yacc.c  */
#line 170 "parse.y"
 
 
void main() 
{ 
	extern FILE *yyin;  
	yyin = fopen("a.txt","r");
	yyparse(); 
	if(flag==0) 
		printf("\nEntered program is Valid\n\n"); 
} 

void yyerror(const char *str) 
{ 
	fprintf(stderr,"Error | Line: %d\n%s\n",yylineno,str); 
	flag=1; 
} 

