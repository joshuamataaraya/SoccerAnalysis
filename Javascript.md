
[Source](http://www.jslint.com/help.html "Permalink to JSLint: Help")

#ECMAScript Standard integrated with JSLint modifications

## import export

The ES6 module feature will be an important improvement over JavaScript's global variables as a means of linking separate files together. **JSLint** recognizes a small but essential subset of the module syntax.

	`import `name` from `stringliteral`;`

The stringliteral identifies a resource. The value (usually a function or object) exported from that resource can be accessed with the name. The stringliteral will be listed in the function report.

	`export default `value`;`

The value (usually a function or object) will be exported from this file.

## Directives

**JSLint** provides three directives that may be placed in a file to manage **JSLint**'s behavior. Use of these directives is optional. If they are used, they should be placed in a source file before the first statement. They are written in the form of a comment, where the directive name is placed immediately after the opening of the comment before any whitespace. The three directives are `global`, `jslint`, and `property`. Directives in a file are stronger than options selected from the UI or passed with the option object.

## /\*global\*/

The `/*global*/` directive is used to specify a set of globals (usually functions and objects containing functions) that are available to this file. This was commonly used in browsers to link source files together before ES6 modules appeared. Use of global variables is strongly discouraged, but unfortunately web browsers require their use. The `/*global*/` directive can only be used when the Assume a browser option is selected.

Each of the names listed will indicate a read-only global variable. The names are separated by `,` comma. This directive should not be used if `import` or `export` is used. This directive inhibits warnings, but it does not declare the names in the execution environment.

For example:

	`/*global`

	`ADSAFE, report, jslint`

	`*/`

instructs **JSLint** to not give warnings about the global variables `ADsafe`, `report`, and `jslint`. However, if any of those names are expected to be supplied by other files and those other files fail to do so, then execution errors will result. It is usually better to use top-level variable declarations instead:

        var ADSAFE;
        var report;
        var jslint;

Using `var` in this way allows comparing a global variable to the `undefined` value to determine whether it is has been used in the global context.

## /\*jslint\*/

The `/*jslint*/` directive allows for the control of several options. You can access the full table on [JSLint.com/help][6] /*jslint*/ section.



## /\*property\*/

The `/*property*/` directive is used to declare a list of property identifiers that are used in the file. Each property name in the program is looked up in this list. If a name is not found, that indicates an error, most likely a typing error.

The list can also be used to evade some of **JSLint**'s naming rules.

**JSLint** can build the `/*property*/` list for you. At the bottom of its report, **JSLint** displays a `/*property*/` directive. It contains all of the names that were used with dot notation, subscript notation, and object literals to name the properties of objects. You can look through the list for misspellings. You can copy the `/*property*/` directive to the top of your script file. **JSLint** will check the spelling of all property names against the list. That way, you can have **JSLint** look for misspellings for you.

For example,

	/*property
		charAt, slice, _$_
	*/

## ignore

**JSLint** introduces a new reserved word: `ignore`. It is used in parameter lists and in `catch` clauses to indicate a parameter that will be ignored. Unused warnings will not be produced for `ignore`.

    function handler(ignore, value) {
        return do_something_useful(value);
    }

## var let const

JavaScript provides three statements for declaring variables: `var`, `let`, and `const`. The `var` statement suffers from a bad practice called hoisting. The `let` statement does not do hoisting and respects block scope. The `const` statement is like `let` except that it marks the variable (but not its contents) as read only, making it an error to attempt to assign to the variable. When given a choice, `const` is the best, `var` is the worst. Use of `let` and `const` require the `es6` option.

**JSLint** uses the intersection of the `var` rules and the `let` rules, and by doing so avoids the errors related to either. A name should be declared only once in a function. It should be declared before it is used. It should not be used outside of the block in which it is declared. A variable should not have the same name as a variable or parameter in an outer function. Do not mix `var` and `let`. Declare one name per statement.

## = == ===

Fortran made a terrible mistake in using the equality operator as its assignment operator. That mistake has been replicated in most languages since then. C compounded that mistake by making `==` its equality operator. The visual similarity is a source of errors. JavaScript compounded this further by making `==` a type coercing comparison operator that produces false positive results. This was mitigated by adding the `===` operator, leaving the broken `==` operator in place.

**JSLint** attempts to minimize errors by the following rules:

`==` is not allowed. This avoids the false positives, and increases the visual distance between `=` and `===`. Assignments are not allowed in expression position, and comparisons are not allowed in statement position. This also reduces confusion.

## Semicolon

JavaScript uses a C-like syntax that requires the use of semicolons to delimit certain statements. JavaScript attempts to make those semicolons optional with an automatic semicolon insertion mechanism, but it does not work very well. Automatic semicolon insertion was added to make things easier for beginners. Unfortunately, it sometimes fails. Do not rely on it unless you are a beginner.

**JSLint** expects that every statement be followed by `;` except for `for`, `function`, `if`, `switch`, `try`, and `while`. **JSLint** does not expect to see unnecessary semicolons, the empty statement, or empty blocks.

## function =>

JavaScript has four syntactic forms for making function objects: function statements, function expressions, enhanced object literals, and the `=&gt;` fart operator.

The function statement creates a variable and assigns the function object to it. It should be used in a file or function body, but not inside of a block.

function name(parameters) {
	statements
	}

The function expression unfortunately looks like the function statement. It may appear anywhere that an expression may appear, but not in statement position and not in a loop. It produces a function object but does not create a variable in which to store it.

	function (parameters) {
		statements
	}

The enhanced object literal provides an ES6 shorthand for creating a property whose value is a function, saving you from having to type `:` colon and `function`.

	{
		name(parameters){
			statements
		}
	}


Finally, ES6 provides an even shorter form of function expression that leaves out the words `function` and `return`:

	(parameters) => expression

**JSLint** requires the parens around the parameters, and forbids a `{` left brace after the `=&gt;` fart to avoid syntactic ambiguity.

## Comma

The `,` comma operator is unnecessary and can mask programming errors.

**JSLint** expects to see the comma used as a separator, but not as an operator. It does not expect to see elided elements in array literals. A comma should not appear after the last element of an array literal or object literal.

## Blocks

**JSLint** expects blocks with `function`, `if`, `switch`, `while`, `for`, `do`, and `try` statements and nowhere else.

**JSLint** expects that `if`, `while`, `do` and `for` statements will be made with blocks `{`that is, with statements enclosed in braces`}`.

JavaScript allows an `if` to be written like this:

    if (_condition_)
        _statement_;

That form is known to contribute to mistakes in projects where many programmers are working on the same code. That is why **JSLint** expects the use of a block:

    if (_condition_) {
        _statements_;
    }

Experience shows that this form is more resilient.

## Expression Statements

An expression statement is expected to be an assignment or a function/method call. All other expression statements are considered to be errors.

## for

**JSLint** does not recommend use of the `for` statement. Use array methods like `forEach` instead. The `for` option will suppress some warnings. The forms of `for` that **JSLint** accepts are restricted, excluding the new ES6 forms.

## for in

**JSLint** does not recommend use of the `for` `in` statement. Use `Object.keys` instead.

The `for` `in` statement allows for looping through the names of all of the properties of an object. Unfortunately, it also loops through all of the properties that were inherited through the prototype chain. This has the bad side effect of serving up method functions when the interest is in data properties. If a program is written without awareness of this situation, then it can fail.

The body of every `for` `in` statement should be wrapped in an `if` statement that does filtering. It can select for a particular type or range of values, or it can exclude functions, or it can exclude properties from the prototype. For example,

    for (name in object) {
        if (object.hasOwnProperty(name)) {
            ....
        }
    }

Note that the above code will fail if the object contains a property named `hasOwnProperty`. Use `Object.keys` instead.

## With

A common error in `switch` statements is to forget to place a `break` statement after each case, resulting in unintended fall-through. **JSLint** expects that the statement before the next `case` or `default` is one of these: `break`, `return`, or `throw`.

The `with` statement was intended to provide a shorthand in accessing properties in deeply nested objects. Unfortunately, it behaves very badly when setting new properties. Never use the `with` statement.

**JSLint** does not expect to see a `with` statement.

## Labels

JavaScript allows any statement to have a label, and labels have a separate name space. **JSLint** is more strict.

**JSLint** expects labels only on statements that interact with `break`: `switch`, `while`, `do`, and `for`. **JSLint** expects that labels will be distinct from vars and parameters.

## Unreachable Code

**JSLint** expects that a `return`, `break`, or `throw` statement will be followed by a `}` right brace or `case` or `default`.

## Confusing Pluses and Minuses

**JSLint** expects that `+` will not be followed by `+` or `++`, and that `-` will not be followed by `-` or `\--`. A misplaced space can turn `\+ +` into `++`, an error that is difficult to see. Use parens to avoid confusion.

## ++and--

The `++` increment and `\--` decrement operators have been known to contribute to bad code by encouraging excessive trickiness. They are second only to faulty architecture in enabling to viruses and other security menaces. Also, preincrement/postincrement confusion can produce off-by-one errors that are extremely difficult to diagnose. Fortunately, they are also complete unnecessary. There are better ways to add 1 to a variable.

It is best to avoid these operators entirely and rely on `+=` and `-=` instead.

## Void

In most C-like languages, `void` is a type. In JavaScript, `void` is a prefix operator that always returns `undefined`. **JSLint** does not expect to see `void` because it is confusing and not very useful.

## Regular Expression

Regular expressions are written in a terse and cryptic notation. **JSLint** looks for problems that may cause portability problems. It also attempts to resolve visual ambiguities by recommending explicit escapement.

JavaScript's syntax for regular expression literals overloads the `/` slash character. To avoid ambiguity, **JSLint** expects that the character preceding a regular expression literal is a `(` left paren or `=` equal or `:` colon or `,` comma character.

## This

Having `this` in the language makes it harder to talk about the language. It is like pair programming with Abbott and Costello.

Avoid using `this`. Warnings about `this` can be suppressed with `option.this`.

## Constructors and new

Constructors are functions that are designed to be used with the `new` prefix. The `new` prefix creates a new object based on the function's `prototype`, and binds that object to the function's implied `this` parameter. If you neglect to use the `new` prefix, no new object will be made and `this` will be bound to the global object.

**JSLint** enforces the convention that constructor functions be given names with initial uppercase. **JSLint** does not expect to see a function invocation with an initial uppercase name unless it has the `new` prefix. **JSLint** does not expect to see the `new` prefix used with functions whose names do not start with initial uppercase.

**JSLint** does not expect to see the wrapper forms `new Number`, `new String`, `new Boolean`.

**JSLint** does not expect to see `new Object`. Use `Object.create(null)` instead.

## Whitespace

**JSLint** has a specific set of rules about the use of whitespace. Where possible, these rules are consistent with centuries of good practice with literary style.

The indentation increases by 4 spaces when the last token on a line is `{` left brace, [ left bracket, `(` left paren. The matching closing token will be the first token on a line, restoring the previous indentation.

The ternary operator can be visually confusing, so `?` question mark and `:` colon always begin a line and increase the indentation by 4 spaces.

    return (the_token.id === "(string)" || the_token.id === "(number)")
        ? String(the_token.value)
        : the_token.id;

If `.` period is the first character on a line, the indentation is increased by 4 spaces.

A `var`, `let`, or `const` statement will also cause an indentation if it declares two or more variables, and if the second variable is not on the same line as the start of the statement.

Long lines can be continued on the next line with 8 spaces added to the current indentation. Those 8 spaces do not change the current indentation. It is 8 to avoid confusion with ordinary indentation.

The word `function` is always followed with one space.

Clauses (`case`, `catch`, `default`, `else`, `finally`) are not statements and so should not be indented like statements.

Spaces are used to make things that are not invocations look less like invocations.

Tabs and spaces should not be mixed. We should pick just one in order to avoid the problems that come from having both. Personal preference is an extremely unreliable criterion. Neither offers a powerful advantage over the other. Fifty years ago, tab had the advantage of consuming less memory, but Moore's Law has eliminated that advantage. Space has one clear advantage over tab: there is no reliable standard for how many spaces a tab represents, but it is universally accepted that a space occupies a space. So use spaces. You can edit with tabs if you must, but make sure it is spaces again before you commit. Maybe someday we will finally get a universal standard for tabs, but until that day comes, the better choice is spaces.

## Not Recommended
There are characters that are handled inconsistently in some browsers, and so must be escaped when placed in strings.

    u0000-u001f
    u007f-u009f
    u00ad
    u0600-u0604
    u070f
    u17b4
    u17b5
    u200c-u200f
    u2028-u202f
    u2060-u206f
    ufeff
    ufff0-uffff

