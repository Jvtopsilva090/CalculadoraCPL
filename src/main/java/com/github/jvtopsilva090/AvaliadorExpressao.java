package principal;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class AvaliadorExpressao {
    public static String avaliar(String expressao) throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
        Object resultado = engine.eval(expressao);
        return resultado.toString();
    }
}

