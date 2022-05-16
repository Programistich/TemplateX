fun main() {
    val reader = TemplateReader("example.toml")
    println(reader.get("Hello", "ua"))
}
