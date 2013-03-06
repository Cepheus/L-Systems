/* Generated By:JavaCC: Do not edit this line. Analyzer.java */
        package parser.IOmanager;

        import java.util.ArrayList;

        import parser.*;

        /**
	 * classe permettant l'analyse d'un fichier contenant des grammaires.
	 * Peut aussi être utilisée pour analyser des règles écrites dans le programme. les grammaires du fichier sont
	 * stockées dans leur ordre d'appartition dans une liste de grammaires: Analyzer.getGrammars().
	 * @author xinouch
	 */
        public class Analyzer implements AnalyzerConstants {
                private Grammar current;
                private ArrayList<Grammar> grammars = new ArrayList<Grammar>();

                /**
		 * @return the current
		 */
                public Grammar getCurrent ()
                {
                        return current;
                }

                /**
		 * @param current the current to set
		 */
                public void setCurrent (Grammar current)
                {
                        this.current = current;
                }

                /**
		 * @return the grammars
		 */
                public ArrayList<Grammar> getGrammars ()
                {
                        return grammars;
                }

                public static void main (String[] args) throws ParseException, NumberFormatException, BadFileException
                {
                        Analyzer analyz = new Analyzer(System.in, "UTF-8");

                        analyz.validationStart();

                        for (int i = 0; i < analyz.getGrammars().size(); i++)
                                System.out.println(analyz.getGrammars().get(i));
                }

/* ****************************
 * REAL FUNCTIONS DEFINITION
 * ***************************/

/**
 * Start the parse of a file containing some grammars
 * @throws ParseException en cas d'erreur de parsing
 * @throws NumberFormatException en cas d'erreur de format des nombres
 * @throws BadFileException
 */
  final public void validationStart() throws ParseException, NumberFormatException, BadFileException {
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ENDL:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      jj_consume_token(ENDL);
    }
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SEQUENCE:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
                        current = new Grammar();
                        current.getUsableSymbols().append(new Symbol('\u03b5', Symbol.S_NULLCHARACTER));
      validationNameAndType();
      jj_consume_token(OPEN_BRA);
      validationSymbols();
      validationAxiom();
      validationAngle();
      validationRules();
      jj_consume_token(CLOSE_BRA);
                        grammars.add(current);
      label_3:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case ENDL:
          ;
          break;
        default:
          jj_la1[2] = jj_gen;
          break label_3;
        }
        jj_consume_token(ENDL);
      }
    }
    jj_consume_token(0);
  }

  final private void validationNameAndType() throws ParseException {
        Token t;
    t = jj_consume_token(SEQUENCE);
    label_4:
    while (true) {
      jj_consume_token(ENDL);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ENDL:
        ;
        break;
      default:
        jj_la1[3] = jj_gen;
        break label_4;
      }
    }
          current.setName(t.image);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case DOL:
      jj_consume_token(DOL);
                  current.setType(Grammar.TYPE_DOL);
      break;
    case SOL:
      jj_consume_token(SOL);
                  current.setType(Grammar.TYPE_SOL);
      break;
    case DXL:
      jj_consume_token(DXL);
                  current.setType(Grammar.TYPE_DXL);
      break;
    case SXL:
      jj_consume_token(SXL);
                  current.setType(Grammar.TYPE_SXL);
      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ENDL:
        ;
        break;
      default:
        jj_la1[5] = jj_gen;
        break label_5;
      }
      jj_consume_token(ENDL);
    }
  }

  final private void validationSymbols() throws ParseException {
        Token t;
        Symbol sym;
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ENDL:
        ;
        break;
      default:
        jj_la1[6] = jj_gen;
        break label_6;
      }
      jj_consume_token(ENDL);
    }
    jj_consume_token(SYMBOLS);
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ENDL:
        ;
        break;
      default:
        jj_la1[7] = jj_gen;
        break label_7;
      }
      jj_consume_token(ENDL);
    }
    jj_consume_token(OPEN_BRA);
    label_8:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ENDL:
        ;
        break;
      default:
        jj_la1[8] = jj_gen;
        break label_8;
      }
      jj_consume_token(ENDL);
    }
    label_9:
    while (true) {
      t = jj_consume_token(SYMBOL);
                  sym = new Symbol(t.image.charAt(0));
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COLON:
        jj_consume_token(COLON);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case FORWARD:
          jj_consume_token(FORWARD);
                                  sym.setInterpretation(Symbol.S_FORWARD);
          break;
        case TURNLEFT:
          jj_consume_token(TURNLEFT);
                                  sym.setInterpretation(Symbol.S_TURNLEFT);
          break;
        case TURNRIGHT:
          jj_consume_token(TURNRIGHT);
                                  sym.setInterpretation(Symbol.S_TURNRIGHT);
          break;
        case TURNUP:
          jj_consume_token(TURNUP);
                                  sym.setInterpretation(Symbol.S_TURNUP);
          break;
        case TURNDOWN:
          jj_consume_token(TURNDOWN);
                                  sym.setInterpretation(Symbol.S_TURNDOWN);
          break;
        case ROLLLEFT:
          jj_consume_token(ROLLLEFT);
                                  sym.setInterpretation(Symbol.S_ROLLLEFT);
          break;
        case ROLLRIGHT:
          jj_consume_token(ROLLRIGHT);
                                  sym.setInterpretation(Symbol.S_ROLLRIGHT);
          break;
        case ABOUTTURN:
          jj_consume_token(ABOUTTURN);
                                  sym.setInterpretation(Symbol.S_ABOUTTURN);
          break;
        case SAVEPOSITION:
          jj_consume_token(SAVEPOSITION);
                                  sym.setInterpretation(Symbol.S_SAVEPOSITION);
          break;
        case RESTOREPOSITION:
          jj_consume_token(RESTOREPOSITION);
                                  sym.setInterpretation(Symbol.S_RESTOREPOSITION);
          break;
        default:
          jj_la1[9] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      default:
        jj_la1[10] = jj_gen;
        ;
      }
      label_10:
      while (true) {
        jj_consume_token(ENDL);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case ENDL:
          ;
          break;
        default:
          jj_la1[11] = jj_gen;
          break label_10;
        }
      }
                  current.getUsableSymbols().append(sym);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SYMBOL:
        ;
        break;
      default:
        jj_la1[12] = jj_gen;
        break label_9;
      }
    }
    jj_consume_token(CLOSE_BRA);
    label_11:
    while (true) {
      jj_consume_token(ENDL);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ENDL:
        ;
        break;
      default:
        jj_la1[13] = jj_gen;
        break label_11;
      }
    }
  }

  final private void validationAxiom() throws ParseException, BadFileException {
        Token t;
        Symbol sym = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case AXIOM:
      jj_consume_token(AXIOM);
      jj_consume_token(COLON);
      t = jj_consume_token(SYMBOL);
                        sym = current.getUsableSymbols().find(t.image.charAt(0));
                        if (sym == null)
                                {if (true) throw new BadFileException("In axiom definition: symbol " + t.image + " not declared.");}
                        current.getAxiom().append(sym);
      break;
    case LONGAXIOM:
      jj_consume_token(LONGAXIOM);
      jj_consume_token(COLON);
      t = jj_consume_token(SEQUENCE);
                        String s = t.image;
                        final int size = s.length();
                        for (int i = 0; i < size; i++)
                        {
                                sym = current.getUsableSymbols().find(s.charAt(i));
                                if (sym == null)
                                        {if (true) throw new BadFileException("In axiom definition: symbol " + s.charAt(i) + " not declared.");}
                                current.getAxiom().append(sym);
                        }
      break;
    default:
      jj_la1[14] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    label_12:
    while (true) {
      jj_consume_token(ENDL);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ENDL:
        ;
        break;
      default:
        jj_la1[15] = jj_gen;
        break label_12;
      }
    }
  }

  final private void validationAngle() throws ParseException, NumberFormatException {
        Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ANGLE:
      jj_consume_token(ANGLE);
      jj_consume_token(COLON);
      t = jj_consume_token(INTEGER);
                  current.setAngle(Integer.parseInt(t.image));
      label_13:
      while (true) {
        jj_consume_token(ENDL);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case ENDL:
          ;
          break;
        default:
          jj_la1[16] = jj_gen;
          break label_13;
        }
      }
      break;
    default:
      jj_la1[17] = jj_gen;
      ;
    }
  }

  final private void validationRules() throws ParseException, BadFileException, NumberFormatException {
        Token t;
        Symbol sym = null;
        Rule rule = null;
    jj_consume_token(RULES);
    label_14:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ENDL:
        ;
        break;
      default:
        jj_la1[18] = jj_gen;
        break label_14;
      }
      jj_consume_token(ENDL);
    }
    jj_consume_token(OPEN_BRA);
    label_15:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ENDL:
        ;
        break;
      default:
        jj_la1[19] = jj_gen;
        break label_15;
      }
      jj_consume_token(ENDL);
    }
    label_16:
    while (true) {
                        switch (current.getType())
                        {
                                case Grammar.TYPE_DOL:
                                        rule = new OLRule(true);
                                        break;
                                case Grammar.TYPE_SOL:
                                        rule = new OLRule(false);
                                        break;
                                case Grammar.TYPE_DXL:
                                        rule = new XLRule(true);
                                        break;
                                case Grammar.TYPE_SXL:
                                        rule = new XLRule(false);
                                        break;
                        }
      // Déclaration des symboles
                      t = jj_consume_token(SYMBOL);
                        sym = current.getUsableSymbols().find(t.image.charAt(0));
                        if (sym == null)
                                {if (true) throw new BadFileException("In rules definition: symbol " + t.image + " not declared.");}
                        rule.setPreExpr(sym);
                        // On teste la stochasticité
                        if (current.getType() == Grammar.TYPE_DOL || current.getType() == Grammar.TYPE_DXL)
                        {
                                final int size = current.getRules().size();

                                for (int i = 0; i < size; i++)
                                {
                                        if (current.getRules().get(i).getPreExpr().getCharacter() == sym.getCharacter())
                                                {if (true) throw new BadFileException("In rules definition:\u005cn" +
                                                                t.image + " appear more than once in the left part of a rule declaration while the" +
                                                                                " grammar is determinist");}
                                }
                        }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PREVIOUS:
        jj_consume_token(PREVIOUS);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case SYMBOL:
          t = jj_consume_token(SYMBOL);
          break;
        case SEQUENCE:
          t = jj_consume_token(SEQUENCE);
          break;
        default:
          jj_la1[20] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
                                // on test le type de la grammaire et si on peut ajouter la règle
                                if (current.getType() == Grammar.TYPE_DOL || current.getType() == Grammar.TYPE_SOL)
                                        {if (true) throw new BadFileException("In rules definition:\u005cn" +
                                                        "a precedence has been given while the grammar is 0L:" + t.image + ".");}
                                String s = t.image;
                                final int size = s.length();
                                for (int i = 0; i < size; i++)
                                {
                                        sym = current.getUsableSymbols().find(s.charAt(i));
                                        if (sym == null)
                                                {if (true) throw new BadFileException("In rules definition: symbol " + s.charAt(i) + " not declared.");}
                                        ((XLRule) rule).getPrevExpr().append(sym);
                                }
        break;
      default:
        jj_la1[21] = jj_gen;
        ;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case FOLLOW:
        jj_consume_token(FOLLOW);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case SYMBOL:
          t = jj_consume_token(SYMBOL);
          break;
        case SEQUENCE:
          t = jj_consume_token(SEQUENCE);
          break;
        default:
          jj_la1[22] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
                                // on test le type de la grammaire et si on peut ajouter la règle
                                if (current.getType() == Grammar.TYPE_DOL || current.getType() == Grammar.TYPE_SOL)
                                        {if (true) throw new BadFileException("In rules definition:\u005cn" +
                                                        "a next constraint has been given while the grammar is 0L:" + t.image + ".");}
                                String s = t.image;
                                final int size = s.length();
                                for (int i = 0; i < size; i++)
                                {
                                        sym = current.getUsableSymbols().find(s.charAt(i));
                                        if (sym == null)
                                                {if (true) throw new BadFileException("In rules definition: symbol " + s.charAt(i) + " not declared.");}
                                        ((XLRule) rule).getNextExpr().append(sym);
                                }
        break;
      default:
        jj_la1[23] = jj_gen;
        ;
      }
      jj_consume_token(ARROW);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SYMBOL:
        t = jj_consume_token(SYMBOL);
        break;
      case SEQUENCE:
        t = jj_consume_token(SEQUENCE);
        break;
      case NULL_CHAR:
        t = jj_consume_token(NULL_CHAR);
        break;
      default:
        jj_la1[24] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
                        String s = t.image;
                        final int size = s.length();
                        // cas du caractère ε
                        if (t.image == "\u03b5")
                        {
                                if (t.image.length() != 1)
                                        {if (true) throw new BadFileException("In rules definition: symbol " + t.image + " not alone in the right " +
                                                        "side of the rule.");}
                                sym = current.getUsableSymbols().find(s.charAt(0));
                                rule.getPostExpr().append(sym);
                        }
                        // traitement général
                        else
                        {
                                for (int i = 0; i < size; i++)
                                {
                                        sym = current.getUsableSymbols().find(s.charAt(i));
                                        if (sym == null)
                                                {if (true) throw new BadFileException("In rules definition: symbol " + s.charAt(i) + " not declared.");}
                                        rule.getPostExpr().append(sym);
                                }
                        }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COLON:
        jj_consume_token(COLON);
        t = jj_consume_token(FLOAT);
                          rule.setProba(Double.parseDouble(t.image));
        break;
      default:
        jj_la1[25] = jj_gen;
        ;
      }
      label_17:
      while (true) {
        jj_consume_token(ENDL);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case ENDL:
          ;
          break;
        default:
          jj_la1[26] = jj_gen;
          break label_17;
        }
      }
                  current.getRules().add(rule);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SYMBOL:
        ;
        break;
      default:
        jj_la1[27] = jj_gen;
        break label_16;
      }
    }
    jj_consume_token(CLOSE_BRA);
    label_18:
    while (true) {
      jj_consume_token(ENDL);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ENDL:
        ;
        break;
      default:
        jj_la1[28] = jj_gen;
        break label_18;
      }
    }
  }

  /** Generated Token Manager. */
  public AnalyzerTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[29];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x2000000,0x0,0x2000000,0x2000000,0xf0000,0x2000000,0x2000000,0x2000000,0x2000000,0xffc0,0x8000000,0x2000000,0x0,0x2000000,0xc,0x2000000,0x2000000,0x20,0x2000000,0x2000000,0x0,0x80000000,0x0,0x0,0x0,0x8000000,0x2000000,0x0,0x2000000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x80,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x40,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0xc0,0x0,0xc0,0x1,0xc2,0x0,0x0,0x40,0x0,};
   }

  /** Constructor with InputStream. */
  public Analyzer(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Analyzer(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new AnalyzerTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Analyzer(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new AnalyzerTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Analyzer(AnalyzerTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(AnalyzerTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[40];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 29; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 40; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

        }
