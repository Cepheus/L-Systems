import parser.*;

/**
 * 
 */

/**
 * @author Caelum
 *
 */
public class LSystem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char eps = 'ε';
		
		System.out.println("Bonjour tortue !" + eps);

		//----------------- essai de génération
		Symbol a = new Symbol('a'), b = new Symbol('b');
		ListSymbols firstList = new ListSymbols(), secondList = new ListSymbols(), axiome = new ListSymbols();
		firstList.append(a);
		firstList.append(b);
		secondList.append(a);
		axiome.append(b);
		Rule first = new SimpleDOLRule(a, firstList), second = new SimpleDOLRule(b, secondList);
		Grammar g = new Grammar();
		g.getRules().add(first);
		g.getRules().add(second);
		g.setUsableSymbols(firstList.clone());
		g.setAxiome(axiome);
		
		Generator generator = new Generator(g);
		generator.setTotalIteration(6);
		generator.generate();
		System.out.println(generator.getLastGenerated());
		System.out.println(generator.getGenerated());
		//----------------- essai de génération 2
		Symbol F = new Symbol('f'), plus = new Symbol('+'), moins = new Symbol('-');
		ListSymbols symbols = new ListSymbols(), axiom = new ListSymbols(), post = new ListSymbols();
		symbols.append(F);
		symbols.append(moins);
		symbols.append(plus);
		axiom.append(F);
		post.append(F);
		post.append(plus);
		post.append(F);
		post.append(moins);
		post.append(F);
		post.append(moins);
		post.append(F);
		post.append(plus);
		post.append(F);
		
		Rule rule = new SimpleDOLRule(F, post);
		Grammar g2 = new Grammar();
		g2.getRules().add(rule);
		g2.setUsableSymbols(symbols);
		g2.setAxiome(axiom);
		
		Generator generator2 = new Generator(g2);
		generator2.setTotalIteration(4);
		generator2.generate();
		System.out.println(generator2.getLastGenerated());
		System.out.println(generator2.getGenerated());
	}

}
