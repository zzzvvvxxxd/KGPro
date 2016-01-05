package edu.ecnu.ica.kgpro.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Predicate;

import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.shared.AddDeniedException;

import edu.ecnu.ica.kgpro.base.Entity;
import edu.ecnu.ica.kgpro.base.Relation;
import edu.ecnu.ica.kgpro.base.Triple;
import edu.ecnu.ica.kgpro.dao.JenaRDFOperation;
import edu.ecnu.ica.kgpro.dao.TripleDAO;
import edu.ecnu.ica.kgpro.dao.TripleDAOImpl;

/**
 * 参考org.apache.jena.util.iterator.FilterIterator
 * @author zwq
 *
 */
public class TripleIterator implements Iterator<Triple>{
	protected boolean can = true;
	
	Triple current;
	Statement s;
	
	private StmtIterator iterator;
	protected boolean hasCurrent = false;		//默认初始化false
	private Predicate<Triple> predicate = null;
	
	/**
	 * 后期可能还是会封装成代理类，但是目前不这样做 2015.1.2
	 * 上面这句我在说什么？ 2015.1.5
	 */
	public TripleIterator(StmtIterator iterator) {
		this.iterator = iterator;
	}
	
	public TripleIterator(StmtIterator iterator, Predicate<Triple> pre) {
		this.iterator = iterator;
		predicate = pre;
	}
	
	public boolean hasNext() {
		if (Objects.nonNull(this.predicate)){
			while (this.iterator.hasNext() && !hasCurrent){
	            hasCurrent = predicate.test( current = Common.toTriple(s = this.iterator.next()) );
			}
			return hasCurrent;
		} else {
			return this.iterator.hasNext();
		}
	}

	public Triple next() {
		if (Objects.nonNull(predicate)){
			if (hasCurrent || hasNext()) {
				hasCurrent = false;
				return current;
	        }
		} else {
			return Common.toTriple(iterator.next());
		}
		throw new NoSuchElementException();
	}
	
	public TripleIterator and(Predicate<Triple> p) {
		if(Objects.isNull(this.predicate)){
			this.predicate = p;
		} else {
			this.predicate.and(p);
		}
		return this;
	}

	public TripleIterator or(Predicate<Triple> p) {
		if(Objects.isNull(this.predicate)){
			this.predicate = p;
		} else {
			this.predicate.or(p);
		}
		return this;
	}
	
	public TripleIterator not(Predicate<Triple> p) {
		if(Objects.isNull(this.predicate)){
			this.predicate = p;
		} else {
			this.predicate.and(p.negate());
		}
		return this;
	}
}
