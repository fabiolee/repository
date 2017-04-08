package com.fabiolee.repository;

/**
 * @author fabio.lee
 */
public final class Response {
    public final Request mRequest;
    public final String mResXml;

    private Response(Request mRequest, String mResXml) {
        this.mRequest = mRequest;
        this.mResXml = mResXml;
    }

    /**
     * This instance is equal to all instances of {@link Response} that have equal attribute values.
     *
     * @return {@code true} if {@code this} is equal to {@code mAnother} instance
     */
    @Override
    public boolean equals(Object mAnother) {
        return (this == mAnother) ||
                (mAnother instanceof Response && this.equalsTo((Response) mAnother));
    }

    private boolean equalsTo(Response mAnother) {
        return mRequest.equals(mAnother.mRequest)
                && mResXml.equals(mAnother.mResXml);
    }

    /**
     * Computes a hash code from attributes: {@code mRequest}, {@code mResXml}.
     *
     * @return hashCode value
     */
    @Override
    public int hashCode() {
        int h = 31;
        h = h * 17 + mRequest.hashCode();
        h = h * 17 + mResXml.hashCode();
        return h;
    }

    /**
     * Prints the immutable value {@link Response} with all non-generated and non-auxiliary attribute values.
     *
     * @return A string representation of the value
     */
    @Override
    public String toString() {
        return "Response{"
                + "request=" + mRequest
                + ", resXml=" + mResXml
                + "}";
    }

    public static final class Builder {
        private Request mRequest;
        private String mResXml;

        public Builder request(Request mRequest) {
            if (mRequest == null) {
                throw new IllegalArgumentException("request may not be null.");
            }
            this.mRequest = mRequest;
            return this;
        }

        public Builder resXml(String mResXml) {
            this.mResXml = mResXml;
            return this;
        }

        public Response build() {
            return new Response(mRequest, mResXml);
        }
    }
}
