/*
 *
 * Criteria API for Magnolia CMS (http://www.openmindlab.com/lab/products/mgnlcriteria.html)
 * Copyright(C) 2009-2013, Openmind S.r.l. http://www.openmindonline.it
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package nl.vpro.jcr.criteria.query.criterion;

import lombok.Getter;

import nl.vpro.jcr.criteria.query.Criteria;
import nl.vpro.jcr.criteria.query.JCRQueryException;


/**
 * Represents an order imposed upon a <tt>Criteria</tt> result set
 * @author Federico Grilli
 */
public class Order extends BaseCriterion implements Criterion {

    private static final long serialVersionUID = -1228583450961430360L;

    @Getter
    private boolean ascending;

    @Getter
    private String nodeName;

    /**
     * Constructor for Order.
     */
    protected Order(String nodeName, boolean ascending) {
        this.nodeName = nodeName;
        this.ascending = ascending;
    }

    @Override
    @Deprecated
    public String toXPathString(Criteria criteria) throws JCRQueryException {
        StringBuilder fragment = new StringBuilder(" ");
        fragment.append(nodeName);
        fragment.append(ascending ? " ascending" : " descending");

        log.debug("xpathString is {} ", fragment);
        return fragment.toString();

    }

    /**
     * Ascending order
     * @param propertyName jcr property name, e.g. "@title"
     * @return Order
     */
    public static Order asc(String propertyName) {
        return new Order(propertyName, true);
    }

    /**
     * Descending order
     * @param propertyName jcr property name, e.g. "@title"
     * @return Order
     */
    public static Order desc(String propertyName) {
        return new Order(propertyName, false);
    }

    @Override
    public String toString() {
        return nodeName + ' ' + (ascending ? "ascending" : "descending");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (ascending != order.ascending) return false;
        return nodeName != null ? nodeName.equals(order.nodeName) : order.nodeName == null;

    }

    @Override
    public int hashCode() {
        int result = (ascending ? 1 : 0);
        result = 31 * result + (nodeName != null ? nodeName.hashCode() : 0);
        return result;
    }
}
