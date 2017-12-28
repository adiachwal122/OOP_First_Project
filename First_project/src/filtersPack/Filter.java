package filtersPack;

import java.util.List;

import databasePack.Network;

/**
 * The abstract class Filter.
 */
public abstract class Filter {
/*
 * Filter function
 * After Building Filter By - object
 * run this function
 * @return String
 * */
public abstract String filter();
/*
 * comperable function
 * implement by each filter 
 * */
public abstract boolean comperable();
/*
 * get function
 * implement by each filter 
 * */
public abstract List<List<Network>> getFilteredFile();
}


