package com.cs1e;

/**
 * Hello world!
 *
 */
public class App 
{
    static Database database;

    public static void main( String[] args )
    {
        database = new Database();

        System.err.print(database.users.get(0).stringify());
    }
}
