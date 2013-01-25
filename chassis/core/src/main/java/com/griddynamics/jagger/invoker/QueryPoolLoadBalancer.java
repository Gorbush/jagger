/*
 * Copyright (c) 2010-2012 Grid Dynamics Consulting Services, Inc, All Rights Reserved
 * http://www.griddynamics.com
 *
 * This library is free software; you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software Foundation; either
 * version 2.1 of the License, or any later version.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.griddynamics.jagger.invoker;

import com.google.common.collect.ImmutableList;
import com.griddynamics.jagger.util.Pair;

import java.util.Iterator;
import java.util.List;

public abstract class QueryPoolLoadBalancer<Q, E> implements LoadBalancer<Q, E> {

    protected Iterable<Q> queryProvider;
    protected Iterable<E> endpointProvider;

    public QueryPoolLoadBalancer(){
    }

    public QueryPoolLoadBalancer(Iterable<Q> queryProvider, Iterable<E> endpointProvider){
        this.queryProvider = queryProvider;
        this.endpointProvider = endpointProvider;
    }

    public void setQueryProvider(Iterable<Q> queryProvider){
        //fix!!!
        this.queryProvider = ImmutableList.copyOf(queryProvider);
    }

    public void setEndpointProvider(Iterable<E> endpointProvider){
        //fix!!!
        this.endpointProvider = ImmutableList.copyOf(endpointProvider);
    }

    @Override
    public final Iterator<Pair<Q, E>> iterator() {
        return provide();
    }

    @Override
    public int endpointSize() {
        Iterator<E> iterator = endpointProvider.iterator();
        int size = 0;
        while (iterator.hasNext()){
            iterator.next();
            size++;
        }
        return size;
    }

    @Override
    public int querySize() {
        Iterator<Q> iterator = queryProvider.iterator();
        int size = 0;
        while (iterator.hasNext()){
            iterator.next();
            size++;
        }
        return size;
    }
}
