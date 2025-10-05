export type FetchOptions = RequestInit & { retries?: number };

export async function retryWithBackoff(
  task: () => Promise<Response>,
  retries = 3,
  delayMs = 200
): Promise<Response> {
  for (let attempt = 0; attempt <= retries; attempt++) {
    try {
      const result = await task();
      if (!result.ok && attempt < retries) {
        await new Promise((resolve) =>
          setTimeout(resolve, delayMs * (attempt + 1))
        ); // why: incremental wait prevents hammering API on transient failures
        continue;
      }
      return result;
    } catch (error) {
      if (attempt === retries) {
        throw error;
      }
      await new Promise((resolve) => setTimeout(resolve, delayMs * (attempt + 1)));
    }
  }
  throw new Error("retryWithBackoff exhausted");
}
