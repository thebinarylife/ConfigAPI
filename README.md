# ConfigAPI

Clone and add the compiled or uncompiled source to your library.

#### Utilizing Config Class 

Create a new config using `new Config(String name, String path)`

You can now create subsections and set methods via `set(String key, Object o)`
If you need to load an existing configuration use `ConfigLoader.loadConfig(path, filename)`
