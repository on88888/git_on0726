package com.atguigu.hive.test;

import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.udf.generic.AbstractGenericUDAFResolver;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFEvaluator;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFParameterInfo;

/**
 * @Author 0726
 * @ClassName My_UDAF
 * @createTime 2021年11月23日 20:16
 */
public class My_UDAF extends AbstractGenericUDAFResolver {
    @Override
    public GenericUDAFEvaluator getEvaluator(GenericUDAFParameterInfo info) throws SemanticException {
        // Type-checking goes here!

        return new GenericUDAFHistogramNumericEvaluator();
    }

    public static class GenericUDAFHistogramNumericEvaluator extends GenericUDAFEvaluator {
        @Override
        public AggregationBuffer getNewAggregationBuffer() throws HiveException {
            return null;
        }

        @Override
        public void reset(AggregationBuffer agg) throws HiveException {

        }

        @Override
        public void iterate(AggregationBuffer agg, Object[] parameters) throws HiveException {

        }

        @Override
        public Object terminatePartial(AggregationBuffer agg) throws HiveException {
            return null;
        }

        @Override
        public void merge(AggregationBuffer agg, Object partial) throws HiveException {

        }

        @Override
        public Object terminate(AggregationBuffer agg) throws HiveException {
            return null;
        }
        // UDAF logic goes here!
    }
}
