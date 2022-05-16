import org.tomlj.Toml
import org.tomlj.TomlParseResult
import org.tomlj.TomlTable

/**
 * @property nameFile content .toml file */
class TemplateReader(
    private val nameFile: String
) {
    private val resourceAsStream = this::class.java.classLoader.getResourceAsStream(nameFile)
        ?: throw RuntimeException("Toml file $nameFile is missing")
    private val tomlParse: TomlParseResult = Toml.parse(resourceAsStream)

    private val dictionary: MutableMap<String, HashMap<String, String>> = hashMapOf()

    init {
        tomlParse.dottedKeySet().map { it.split(".").first() }.distinct().forEach { table ->
            val tomlTable: TomlTable = tomlParse.getTableOrEmpty(table)
            val keys = tomlTable.toMap()
            dictionary[table] = keys as HashMap<String, String>
        }
    }

    /**
     @param table name of block

     @param tag name of line

     @param values replaceable content

     @return string by table + key + replace

     For example:

     &#91;Hello&#93; <- Table

     en="Hello {} {}" <- Key1

     ua="Привіт {} {}" <- Key2

     {} - replaceable content
     */
    fun get(table: String, key: String, vararg values: String): String {
        val contentByTable = dictionary[table] ?: throw InternalException("Map by table $table is missing")
        val textByKey = contentByTable[key] ?: throw InternalException("Map by table $table with key $key is missing")
        var textByReplaceValues = textByKey
        values.forEach {
            textByReplaceValues = textByReplaceValues.replaceFirst("{}", it)
        }
        return textByReplaceValues.trimIndent()
    }

    class InternalException(override val message: String) : RuntimeException(message)
}
