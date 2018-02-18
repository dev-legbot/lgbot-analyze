public class ParsedResult {
//    public final String url;
//    public final List<Dom> doms;

    public ParsedResult (String domEnumerator) {

    }

    public static class Dom {
        public int count = 0;
        public final String name;

        public Dom(String name) {
            this.name = name;
        }

        public Dom incrementDomCount() {
            this.count++;
            return this;
        }
    }
}
